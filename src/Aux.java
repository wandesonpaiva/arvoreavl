public class Aux{

  public static void main(String[] args) {
    Arvore avl = new Arvore();
    
    avl.inserir(15);
    avl.inserir(8);
    avl.inserir(90);
    avl.inserir(44);
    avl.inserir(65);
    
    avl.remover(9);
    System.out.println("--- in ordem ---");
    avl.inOrder(avl.raiz);
  }
}
