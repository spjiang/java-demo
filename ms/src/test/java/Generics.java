import org.example.refect.GenericContainer;
import org.example.refect.GenericNumberContainer;
import org.example.refect.MultiGenericContainer;
import org.example.refect.ObjectContainer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.refect.Test.checkList;

/**
 * Package: PACKAGE_NAME
 *
 * @description: 泛型测试
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-11-02 15:57
 */
public class Generics {

    @Test
    public void TestObject() {
        ObjectContainer myObj = new ObjectContainer();
        // store a string
        myObj.setObj("Test");
        System.out.println("Value of myObj:" + myObj.getObj());
        // store an int (which is autoboxed to an Integer object)
        myObj.setObj(3);
        System.out.println("Value of myObj:" + myObj.getObj());

        List objectList = new ArrayList();
        objectList.add(myObj);
        // We have to cast and must cast the correct type to avoid ClassCastException!
        Integer myStr = (Integer) ((ObjectContainer) objectList.get(0)).getObj();
        System.out.println("myStr: " + myStr);
    }

    @Test
    public void TestGeneric() {
        GenericContainer<Integer> myInt = new GenericContainer<Integer>();
        myInt.setObj(3);  // OK
    }

    @Test
    public void TestGeneric01() {
        List myObjList = new ArrayList();
        // Store instances of ObjectContainer
        for (int x = 0; x <= 10; x++) {
            ObjectContainer myObj = new ObjectContainer();
            myObj.setObj("Test" + x);
            myObjList.add(myObj);
        }
        // Get the objects we need to cast
        for (int x = 0; x <= myObjList.size() - 1; x++) {
            ObjectContainer obj = (ObjectContainer) myObjList.get(x);
            System.out.println("Object Value: " + obj.getObj());
        }

        List<GenericContainer> genericList = new ArrayList<GenericContainer>();
        // Store instances of GenericContainer
        for (int x = 0; x <= 10; x++) {
            GenericContainer<String> myGeneric = new GenericContainer<String>();
            myGeneric.setObj(" Generic Test" + x);
            genericList.add(myGeneric);
        }
        // Get the objects; no need to cast to String
        for (GenericContainer<String> obj : genericList) {
            String objectString = obj.getObj();
            // Do something with the string...here we will print it
            System.out.println(objectString);
        }
    }

    @Test
    public void MultiGenericContainer() {
        MultiGenericContainer<String, String> mondayWeather =
                new MultiGenericContainer<String, String>("Monday", "Sunny");
        MultiGenericContainer<Integer, Double> dayOfWeekDegrees =
                new MultiGenericContainer<Integer, Double>(1, 78.0);
        String mondayForecast = mondayWeather.getFirstPosition(); // Works fine with String
        // The following generates "Incompatible types" error and won't compile
        String mondayOutlook = mondayWeather.getSecondPosition();
        double sundayDegrees = dayOfWeekDegrees.getSecondPosition(); // Unboxing occurs
    }

    @Test
    public void TestGenericNumberContainer() {
        GenericNumberContainer<Integer> gn = new GenericNumberContainer<Integer>();
        gn.setObj(3);

    }

    @Test
    public void TestListContainer() {
        // Create List of type Integer
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(2);
        intList.add(4);
        intList.add(6);

        // Create List of type String
        List<String> strList = new ArrayList<String>();
        strList.add("two");
        strList.add("four");
        strList.add("six");

        // Create List of type Object
        List<Object> objList = new ArrayList<Object>();
        objList.add("two");
        objList.add("four");
        objList.add(strList);

        checkList(intList, 3);
        // Output:  The list [2, 4, 6] does not contain the element: 3

        checkList(objList, strList);
        /* Output:  The list [two, four, [two, four, six]] contains
        the element: [two, four, six] */

        checkList(strList, objList);
        /* Output:  The list [two, four, six] does not contain
        the element: [two, four, [two, four, six]] */
    }
}
