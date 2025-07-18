package com.hy.blog.common.loader;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

@Component
public class SqlXmlLoader {

    public String getSqlFromXml(String id, String version, Map<String, Object> param) {
        try {
            // application.yml에 지정된 위치에 맞게 파일 경로 처리
            ClassPathResource resource = new ClassPathResource("sql/user.xml");  // 또는 param에 따라 동적으로

            InputStream inputStream = resource.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            doc.getDocumentElement().normalize();

            NodeList sqlList = doc.getElementsByTagName("sql");

            for (int i = 0; i < sqlList.getLength(); i++) {
                Element sqlElement = (Element) sqlList.item(i);
                if (id.equals(sqlElement.getAttribute("id")) &&
                        version.equals(sqlElement.getAttribute("version"))) {

                    NodeList statements = sqlElement.getElementsByTagName("statement");
                    if (statements.getLength() > 0) {
                        String rawSql = statements.item(0).getTextContent();

                        // Velocity 파싱
                        VelocityContext context = new VelocityContext();
                        for (Map.Entry<String, Object> entry : param.entrySet()) {
                            context.put(entry.getKey(), entry.getValue());
                        }

                        StringWriter writer = new StringWriter();
                        Velocity.init();
                        Velocity.evaluate(context, writer, "SQLTemplate", rawSql);

                        return writer.toString();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
