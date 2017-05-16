
public class No {
  private No pai;
  private No filhoEsquedo;
  private No filhoDireito;
  private int valor;
  private int fb;

  public No(int valor){
    this.valor = valor;
    this.fb = 0;
  }

  public No getPai(){
    return pai;
  }

  public No getEsquerdo(){
    return filhoEsquedo;
  }

  public No getDireito(){
    return this.filhoDireito;
  }

  public int getValor(){
    return valor;
  }

  public int getFb(){
    return fb;
  }

  public void setPai(No pai){
    this.pai = pai;
  }

  public void setEsquerdo(No esquerdo){
    this.filhoEsquedo = esquerdo;
  }

  public void setDireito(No direito){
    this.filhoDireito = direito;
  }

  public void setValor(int valor){
    this.valor = valor;
  }

  public void setFb(int fb){
    this.fb = fb;
  }

}
