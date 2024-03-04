import Domain.Cake;
import Domain.Order;
import Repository.DB.DatabaseRepository;
import Repository.LoadRepository;
import Service.DB.OrderServiceDB;
import Service.DB.CakeServiceDB;
import Service.File.OrderServiceTextFile;
import Service.File.CakeServiceTextFile;
import UI.Ui;

import java.time.LocalDate;


public class Main {

    public static void addEntries(CakeServiceTextFile pService, OrderServiceTextFile aService) {


        Cake p1 = new Cake(1, "Mitica", "071", "adresa1");
        Cake p2 = new Cake(2, "Darius", "072", "adresa2");
        Cake p3 = new Cake(3, "Ecaterina", "073", "adresa3");

        Order a1 = new Order(1, p1, LocalDate.parse("2020-05-13"), "headache");
        Order a2 = new Order(2, p2, LocalDate.parse("2019-07-26"), "sick");
        Order a3 = new Order(3, p3, LocalDate.parse("2022-10-03"), "mental issues");

        try {

            pService.add(p1);
            pService.add(p2);
            pService.add(p3);

            aService.add(a1);
            aService.add(a2);
            aService.add(a3);

        } catch (Exception e) {
            System.out.println("Couldn't add the entries! " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        var cakesRepository = LoadRepository.createCakeRepository();
        CakeServiceDB cakesService = new CakeServiceDB((DatabaseRepository<Cake>) cakesRepository);

        var ordersRepository = LoadRepository.createOrderRepository();
        OrderServiceDB ordersService = new OrderServiceDB((DatabaseRepository<Order>) ordersRepository);

        Ui ui = new Ui(cakesService, ordersService);
        ui.main();
    }
}