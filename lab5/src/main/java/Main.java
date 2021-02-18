import ticket.Address;

public class Main {
    public static void main(String[] args) {
//        int a;
//        System.out.println(a);
        System.out.println(new Address("dgf").toString());
        System.out.println(new Address(null).toString());
        System.out.println(new Address("").toString());
    }
}
