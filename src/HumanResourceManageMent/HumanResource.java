package HumanResourceManageMent;
import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
public class HumanResource {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        HumanResource humanResource = new HumanResource();

        String fileName = "D:\\Workspace\\Java Project\\Clock\\src\\HumanResourceManageMent\\test.txt";
        String fileXmlName = "D:\\Workspace\\Java Project\\Clock\\src\\HumanResourceManageMent\\test.xml";
        HRM hrm = new HRM("Man", 10, 4, "a");
        HRM hrm2 = new HRM("Dat", 11, 2, "b");
        HRM hrm1 = new HRM("Hoa",9,3,"c");


        Company company =  new Company();
        company.addHRM(hrm);
        company.addHRM(hrm2);
        company.addHRM(hrm1);

        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(Company.list);
        System.out.println("Written successfully");
        oos.close();

        List<HRM> i;
        ObjectInputStream ois = null;
        ois  = new ObjectInputStream(new FileInputStream(fileName));
        try{
            i = (List<HRM>) ois.readObject();
            System.out.println("Read successfully");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("-----Please choose function-----");
        System.out.println("1. Print all list items");
        System.out.println("2. Choose staff has the largest salary ");
        System.out.println("3. Print the first HRM ");
        System.out.println("4. Choose staff using number room?");
        System.out.println("5. Display HRM ascending of number salary");
        System.out.println("6. Find staff by name or DOB");
        System.out.println("7. Read data in file read.xml");
        System.out.println("8. Write data in file write.xml");
        System.out.println("-----------------------------");

        boolean programme = true;
        while(programme){
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        i.stream().forEach(System.out::println);
                        break;
                    case 2:
                        HRM hrm5 = i.stream().max(Comparator.comparingInt(s -> s.quantity)).orElse(null);
                        System.out.println(hrm5);
                        break;
                    case 3:
                        HRM hrm3 = i.stream().findFirst().orElse(null);
                        System.out.println(hrm3);
                        break;
                    case 4:
                        scanner.nextLine();
                        String c = scanner.nextLine();
                        i.stream().filter(s -> s.unit.equals(c)).forEach(System.out::println);
                        break;
                    case 5:
                        i.stream().sorted(Comparator.comparingInt(s -> s.quantity)).forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("Enter name or year to find");
                        String ii = scanner.nextLine();
                        int cnt = 0;
                        byte[] iii = ii.getBytes();
                        for (int j = 0; j < ii.length(); j++) {
                            if (Character.isLetter(iii[j])) {
                                cnt++;
                            }
                        }
                        if (cnt == ii.length()) {
                            i.stream().filter(s -> s.name.equals(ii)).forEach(System.out::println);
                        } else if (cnt == 0) {
                            i.stream().filter(s -> s.DB == Integer.parseInt(ii)).forEach(System.out::println);

                        }
                        break;
                    case 7:
                        humanResource.writeXML(fileXmlName, i);
                        break;
                    case 8:
                        humanResource.readXML(fileXmlName, i);
                        break;

                }
            }catch (Exception e){
                System.out.println("Error!! Restart menu.");
            }
        }
    }
    void writeXML(String fileName, List<HRM> i){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();


            // root element
            Element rootElement = doc.createElement("Company");
            doc.appendChild(rootElement);
            for(HRM obj : i){

                Element nhanSu = doc.createElement("HRM");
                rootElement.appendChild(nhanSu);


                Element carname = doc.createElement("Name");
                carname.appendChild(doc.createTextNode(obj.name));
                nhanSu.appendChild(carname);

                Element carname1 = doc.createElement("DB");
                carname1.appendChild(doc.createTextNode(String.valueOf(obj.DB)));
                nhanSu.appendChild(carname1);


                Element carname2 = doc.createElement("Quantity");
                carname2.appendChild(doc.createTextNode(String.valueOf(obj.quantity)));
                nhanSu.appendChild(carname2);

                Element carname3 = doc.createElement("Unit");
                carname3.appendChild(doc.createTextNode(obj.unit));
                nhanSu.appendChild(carname3);


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(fileName));
                transformer.transform(source, result);

                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    void readXML(String fileName, List<HRM> i){
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("HRM");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno"));
                    System.out.println("Name : "
                            + eElement
                            .getElementsByTagName("Name")
                            .item(0)
                            .getTextContent());
                    System.out.println("DOB : "
                            + eElement
                            .getElementsByTagName("DB")
                            .item(0)
                            .getTextContent());
                    System.out.println("Quantity : "
                            + eElement
                            .getElementsByTagName("Quantity")
                            .item(0)
                            .getTextContent());
                    System.out.println("Unit : "
                            + eElement
                            .getElementsByTagName("Unit")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

