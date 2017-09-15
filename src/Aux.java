public class Aux{

  public static void main(String[] args) {
    Arvore avl = new Arvore();
    
    avl.inserir(15);
    avl.inserir(8);
    /*avl.inserir(90);
    avl.inserir(44);
    avl.inserir(65);
    /*avl.inserir(22);
    avl.inserir(36);
    avl.inserir(78);
    avl.inserir(84);
    avl.inserir(11);
    avl.inserir(2);
    avl.inserir(19);*/
    
    
    //avl.remover(3);
    //avl.inserir(4);
    //avl.inserir(5);    
    System.out.println("--- in ordem ---");
    avl.inOrder(avl.raiz);

  }

}
