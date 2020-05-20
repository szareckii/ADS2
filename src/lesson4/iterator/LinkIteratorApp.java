package lesson4.iterator;

public class LinkIteratorApp {

    public static void main(String[] args) {
        LinkedList1 list = new LinkedList1();

        LinkInterator itr = new LinkInterator(list);

        itr.insertAfter("Artem", 20);
        itr.insertBefore("Sergey", 10);


        list.display();

        System.out.println("---------");

        itr.insertAfter("Ivan", 35);
        itr.reset();
        itr.insertBefore("Draga", 30);

        list.display();

        System.out.println("---------");

        itr.nextLink();

        if (!itr.atEnd()) {
            itr.deleteCurrent();
        }

        list.display();

        System.out.println("---------");

        System.out.println(itr.getCurrent());
    }
}
