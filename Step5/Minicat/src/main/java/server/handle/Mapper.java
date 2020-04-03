package server.handle;

import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import server.HttpServlet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Mapper
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/3 5:09 PM
 * @Version 1.0
 **/
public class Mapper {

    public static Map<String,HttpServlet> servletMap = new HashMap<>();

    @Data
    class Wrapper {
        HttpServlet instance;
        String urlPattern;

        private void parseWebXml(String docBasePath) {
            InputStream resourceAsStream = null;
            try {
                resourceAsStream = new BufferedInputStream(new FileInputStream(docBasePath + "/web.xml"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            SAXReader saxReader = new SAXReader();

            try {
                Document document = saxReader.read(resourceAsStream);
                Element rootElement = document.getRootElement();

                List<Element> selectNodes = rootElement.selectNodes("//servlet");
                for (int i = 0; i < selectNodes.size(); i++) {
                    Element element =  selectNodes.get(i);
                    Element servletnameElement = (Element) element.selectSingleNode("servlet-name");
                    String servletName = servletnameElement.getStringValue();
                    System.out.println(servletName);
                    Element servletclassElement = (Element) element.selectSingleNode("servlet-class");
                    String servletClass = servletclassElement.getStringValue();

                    File file = new File(docBasePath);
                    URL url = null;
                    try {
                        url = file.toURI().toURL();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    URLClassLoader loader = new URLClassLoader(new URL[]{url});
                    Class<?> clazz = loader.loadClass(servletClass);
                    System.out.println(clazz);

                    try {
                        this.instance = (HttpServlet) clazz.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
                    urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
                    System.out.println(urlPattern);

                }

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Data
    class Context {
        String docBasePath;
        Wrapper wrapper;
    }

    @Data
    class Host {
        List<Context> contextList;
        String host;
        String port;
    }

    // 加载 server.xml
    public void loadServer() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("server.xml");
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();

            // 获取 Service 节点
            List<Element> serviceNodes = rootElement.selectNodes("//Service");
            serviceNodes.forEach(serviceNode -> {
                Host host = new Host();
                // 获取端口号
                Element connectorElement = (Element) serviceNode.selectSingleNode("Connector");
                String port = connectorElement.attributeValue("port");
                System.out.println(port);

                List<Element> hostNodes = serviceNode.selectNodes("//Host");
                hostNodes.forEach(hostNode -> {
                    // 获取 host
                    String hostName = hostNode.attributeValue("name");
                    System.out.println(hostName);

                    host.setHost(hostName);
                    host.setPort(port);

                    // 获取 部署路径
                    String appBase = hostNode.attributeValue("appBase");
                    System.out.println(appBase);
                    List<Element> contextNodes = hostNode.selectNodes("//Context");
                    List<Context> contextList = new ArrayList<>();
                    contextNodes.forEach(contextNode -> {
                        Context context = new Context();
                        // 获取应用路径
                        String docBase = contextNode.attributeValue("docBase");
                        System.out.println(docBase);
                        // context path
                        String contextPath = docBase.substring(docBase.lastIndexOf("/") + 1);

                        context.setDocBasePath(docBase);
                        contextList.add(context);

                        // wrapper
                        Wrapper wapper = new Wrapper();
                        wapper.parseWebXml(docBase);
                        String urlPattern = wapper.getUrlPattern();
                        HttpServlet instance = wapper.getInstance();
                        servletMap.put(host.getHost() + ":" + host.getPort() + "/" + contextPath + urlPattern, instance);
                    });
                });

            });


        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


}
