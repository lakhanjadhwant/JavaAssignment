public class ObjectsCount {
    public static int count=0;
    public static void main(String[] args) {
        
        ObjectsCount obj1 = new ObjectsCount();
        ObjectsCount obj2 = new ObjectsCount();
        ObjectsCount obj3 = new ObjectsCount();
        ObjectsCount obj4 = new ObjectsCount();
        System.out.println("Total no. of objects are : "+count);

    }
    {
        count++;
    }
}