package p1;
public class A {
    public String PublicVar="I am a Public data member of A.class of package p1";
    private String PrivateVar="I am a Private data member of A.class of package p1";
    protected String ProtectedVar="I am a Protected data member of A.class of package p1";
    String DefaultVar="I am a Default data member of A.class of package p1";
    
    public static void main(String[] args) {
        A obj = new A();
        System.out.println(obj.PublicVar);
        System.out.println(obj.PrivateVar);
        System.out.println(obj.ProtectedVar);
        System.out.println(obj.DefaultVar);
    }
}




