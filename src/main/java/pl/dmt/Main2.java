package pl.dmt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Main2 {

    private static final String json = "{\"name\":\"mkyong\",\"age\":29,\"id\":100}";
    private static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<customer id=\"100\">\n" +
            "    <age>29</age>\n" +
            "    <name>mkyong</name>\n" +
            "</customer>";
    private static final String json2 = "{\n" +
            "    \"book\": \n" +
            "    [\n" +
            "        {\n" +
            "            \"title\": \"Beginning JSON\",\n" +
            "            \"author\": \"Ben Smith\",\n" +
            "            \"price\": 49.99\n" +
            "        },\n" +
            " \n" +
            "        {\n" +
            "            \"title\": \"JSON at Work\",\n" +
            "            \"author\": \"Tom Marrs\",\n" +
            "            \"price\": 29.99\n" +
            "        },\n" +
            " \n" +
            "        {\n" +
            "            \"title\": \"Learn JSON in a DAY\",\n" +
            "            \"author\": \"Acodemy\",\n" +
            "            \"price\": 8.99\n" +
            "        },\n" +
            " \n" +
            "        {\n" +
            "            \"title\": \"JSON: Questions and Answers\",\n" +
            "            \"author\": \"George Duckett\",\n" +
            "            \"price\": 6.00\n" +
            "        }\n" +
            "    ],\n" +
            " \n" +
            "    \"price range\": \n" +
            "    {\n" +
            "        \"cheap\": 10.00,\n" +
            "        \"medium\": 20.00\n" +
            "    }\n" +
            "}";

    private static void jsonParse() {
        Filter expensiveFilter = Filter.filter(Criteria.where("price").gt(20.00));
        List<Map<String, Object>> expensive = JsonPath.parse(json2)
                .read("$['book'][?]", expensiveFilter);
        System.out.println(expensive);

        expensiveFilter = Filter.filter(Criteria.where("price").gt(20.00));
        expensive = JsonPath.parse(json2).read("$['book'][?]", expensiveFilter);
        System.out.println(expensive);

        Object dataObject = JsonPath.parse(json).read("$[?(@.id == 100)]");
        System.out.println(dataObject.toString());
    }

    public static void main(String[] args) throws IOException {
        jsonParse();
        /*jsonToXml();
        System.out.println();
        jsonToXmlByJsonOrg();
        System.out.println();
        xmlToJson();
        System.out.println();
        xmlToJsonByJsonOrg();
        System.out.println();
        toXml(getCustomer());
        System.out.println();
        fromXml();
        System.out.println();
        toJson();
        System.out.println();
        fromJson();*/
    }

    private static Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("mkyong");
        customer.setAge(29);
        return customer;
    }

    private static void jsonToXml() {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        // json to class
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = null;
        try {
            customer = objectMapper.readValue(json, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // class to xml
        toXml(customer);
    }

    private static void jsonToXmlByJsonOrg() {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        JSONObject json = new JSONObject(Main2.json);
        String xml = XML.toString(json, "customer");
        System.out.println(xml);
    }

    private static void xmlToJsonByJsonOrg() {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        String json = XML.toJSONObject(xml, true).toString();
        System.out.println(json);
    }

    private static void xmlToJson() throws IOException {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(xml.getBytes());
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(node);
        System.out.println(json);
    }

    private static void toJson() {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String customer = objectMapper.writeValueAsString(getCustomer());
            System.out.println(customer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void fromJson() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Customer customer = objectMapper.readValue(json, Customer.class);
            System.out.println(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toXml(Customer customer) {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(customer, sw);
            System.out.println(sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void fromXml() {
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Reader reader = new StringReader(xml);
            Customer customer = (Customer) jaxbUnmarshaller.unmarshal(reader);
            System.out.println(customer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

@XmlRootElement
class Customer {

    private String name;
    private int age;
    private int id;

    public String getName() {
        return name;
    }

    @XmlElement
    void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age &&
                id == customer.id &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}