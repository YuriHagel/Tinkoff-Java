package java8InAction.appa;


import java.util.Arrays;

@Author(name = "Raoul")
@Author(name = "Mario")
@Author(name = "Alan")
public class Book {

  public static void main(String[] args) {
    Author[] authors = Book.class.getAnnotationsByType(Author.class);
    Arrays.stream(authors).forEach(a -> System.out.println(a.name()));
  }
}
