package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            ClientService service = new ClientService(Database.getConnection());
            System.out.println(service.create("Vasia Pupkin"));
            System.out.println(service.getById(6).toString());
            service.setName(6l, "Venera Miloska");
            service.deleteById(5l);
            service.listAll().forEach(a -> System.out.println(a.toString()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
