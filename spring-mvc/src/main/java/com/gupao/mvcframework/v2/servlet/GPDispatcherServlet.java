package com.gupao.mvcframework.v2.servlet;



import com.gupao.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {

    //保存application.propeties配置文件中的内容
    private Properties contextConfig = new Properties();

    //保存扫描到的所有类名
    private List<String> classNames = new ArrayList<String>();

    //ioc容器
    private Map<String, Object> ioc = new HashMap<String, Object>();

    private Map<String,Method> handleMapping = new HashMap<String,Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用,运行阶段
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception detail:" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        if(!this.handleMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!");
            return;
        }
        Method method = this.handleMapping.get(url);
        //通过反射拿到method所在class，拿到class之后还是拿到class的名称
        //再调用toLowerFirstCase获得beanName
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        Map<String,String[]> params = req.getParameterMap();

        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramValues = new Object[parameterTypes.length];
        int index = 0;
        for (int i =0 ;i<parameterTypes.length;i++){
            Class parameterType = parameterTypes[i];
            if(parameterType==HttpServletRequest.class){
                paramValues[index] = req;
                index++;
                continue;
            }else if(parameterType==HttpServletResponse.class){
                paramValues[index] = resp;
                index++;
                continue;
            }

//            else if(parameterType == String.class){
            Annotation[][] pa = method.getParameterAnnotations();
            for(int j=0;j<pa.length;j++){
                for(Annotation a:pa[j]){
                    if(a instanceof GPRequestParam){
                        String paramName = ((GPRequestParam) a).value();
                        if(!"".equals(paramName.trim())){
                            if(params.containsKey(paramName)){
//                                for (Map.Entry<String, String[]> param : params.entrySet()) {
                                    String value = Arrays.toString(params.get(paramName)).replaceAll("\\[|\\]","")
                                            .replaceAll("\\s",",");
                                    paramValues[i] = convert(parameterType,value);
//                                    index++;
//                                }
                            }
                        }
                    }
                }
//                }
//                GPRequestParam gpRequestParam = (GPRequestParam)parameterType.getAnnotation(GPRequestParam.class);
//                if(params.containsKey(gpRequestParam.value())){
//                    for (Map.Entry<String, String[]> param : params.entrySet()) {
//                        String value = Arrays.toString(param.getValue())
//                                .replaceAll("\\[|\\]","")
//                                .replaceAll("\\s",",");
//                        paramValues[i] = value;
//                    }
//                }
            }
        }
        method.invoke(ioc.get(beanName),paramValues);
    }

    private Object convert(Class<?> type,String value){
        if(Integer.class == type){
            return Integer.valueOf(value);
        }else if(String.class == type){
            return  value;

        }
        return value;
    }

    //初始化阶段
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        //3.初始化扫描到的类，并且将它们放入到IOC容器之中
        doInstance();
        //4.完成依赖注入
        doAutowired();
        //5.初始化HandlerMapping
        initHandlerMapping();

        System.out.println("GP Spring end");
    }

    //初始化url和method的一对一对应关系
    private void initHandlerMapping() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(GPController.class)){continue;}
            //保存写在类上面的@GPRequestMapping("/demo")
            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                handleMapping.put(url,method);
                System.out.println("Mapped:" + url + "," + method);
            }
        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){return;}
        for(Map.Entry<String,Object> entry:ioc.entrySet()){
            //所有的，特定的字段，包括private/protected/default
            //正常来说，普通的oop变成只能拿到public的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields){
                if(!field.isAnnotationPresent(GPAutowired.class)){continue;}
                GPAutowired gpAutowired = field.getAnnotation(GPAutowired.class);
                //如果用户没有自定义beanName，默认就根据类型注入
                String beanName = gpAutowired.value().trim();
                if("".equals(beanName)){
                    //获得接口的类型，作为key待会拿这个key到ioc容器中去取值
                    beanName = field.getType().getName();

                }
                //如果是public以外的修饰符，只要加了@Autowired注解，都要强制赋值
                //反射中叫做暴力访问
                field.setAccessible(true);
                try {
                    //用反射机制，动态给字段赋值
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void doInstance() {
        //初始化，为DI做准备
        if(classNames.isEmpty()){return;}
        try{
            for (String className:classNames){
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(GPController.class)){
                    Object instance = clazz.newInstance();
                    //Spring默认为类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,instance);
                }else if (clazz.isAnnotationPresent(GPService.class)){
                    //自定义的beanName
                    GPService gpService = clazz.getAnnotation(GPService.class);
                    String beanName = gpService.value();
                    if("".equals(beanName.trim())){
                        //默认类名首字母小写
                        beanName =  toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);
                    //根据类型自动赋值
                    for(Class<?> i:clazz.getInterfaces()){
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The " + i.getName() + " is exists!");
                        }
                        ioc.put(i.getName(),instance);
                    }
                }else{
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //因为大小写字母的Ascii码相差32，而且大写字母的Ascii码要小于小写字母的Ascii码
        //在java中，对char做算学运算，实际上是对Ascii码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }

    //扫描出相关的类
    private void doScanner(String scanPackage) {
        //scanPackage = com.gupao.demo存储的包路径
        //转换为文件路径，实际上就是把.替换成/就可以了
        //classPath
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());
        for (File file:classPath.listFiles()){
            if(file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());

            }else{
                if(!file.getName().endsWith(".class")){continue;}
                String className = (scanPackage + "." + file.getName()).replace(".class","");
                classNames.add(className);
            }
        }
    }

    //加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        //直接从类路径下找到Spring主配置文件所在的路径
        //并且将其读取出来放到propeeties对象中
        //相当于scanPackage=com.gupao.demo从文件中保存到了内存中
        InputStream fis = null;
        fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            if(null!=fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
