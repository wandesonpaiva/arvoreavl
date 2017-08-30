public class Arvore {

  public No raiz;

  // INSERIR
  public void inserir(int valor){
    No no = new No(valor);
    if (raiz == null){
    	System.out.println(valor + " inserido na raiz");
    	raiz = no;
    } else {
    	No pai = acharPai(valor, raiz);
		if (pai != null){
			no.setPai(pai);
			if (pai.getValor() > valor){
				System.out.println(valor + " inserido a esquerda de " + pai.getValor());
				pai.setEsquerdo(no);
			} else if(pai.getValor() < valor){
				System.out.println(valor + " inserido a direita de " + pai.getValor());
				pai.setDireito(no);
			}
			ajustarFbInsercao(no);
      	} else {
      		System.out.println("valor já existe");
      	}
    }
  }

  // ACHAR PAI
  public No acharPai(int valor, No no){
	  if (valor < no.getValor() && no.getEsquerdo() != null){
		  return acharPai(valor, no.getEsquerdo());
	  } else if(valor > no.getValor() && no.getDireito() != null){
		  return acharPai(valor, no.getDireito());
	  } else if(valor == no.getValor()) {
		  return null;
	  } else {
		  return no;
	  }
  }


  
  // AJUSTAR FB NA INSERÇÃO -- método está bugado --> CORRIGIR 
  public void ajustarFbInsercao(No filho){
	  
    No pai = filho.getPai();
    //int valor = esquerdo.valor;
    // se filho for filho esquerdo do pai
    if (pai != null && pai.getEsquerdo() != null && pai.getEsquerdo().getValor() == filho.getValor()){
      //System.out.println("Antigo FB de " + pai.getValor() + " é: " + pai.getFb());
    	pai.setFb(pai.getFb() + 1);
    	System.out.println("Novo FB de " + pai.getValor() + " é: " + pai.getFb());
    	if (pai.getFb() >= 2){
    		if (pai.getEsquerdo().getFb() < 0){
    			System.out.println("RotacaoDireitaDupla em " + pai.getValor());
    			rotacaoDireitaDupla(pai);
    			return;
    		} else {
    			System.out.println("RotacaoDireitaSimples em " + pai.getValor());
    			rotacaoDireitaSimples(pai);
    			return;
    		}
    	} else if(pai.getFb() != 0) {
    		ajustarFbInsercao(pai);
    	}
   	// se filho for filho direito do pai
    } else if(pai != null && pai.getDireito() != null && pai.getDireito().getValor() == filho.getValor()){
      //System.out.println("Antigo FB de " + pai.getValor() + " é: " + pai.getFb());
    	pai.setFb(pai.getFb() - 1);
    	System.out.println("Novo FB de " + pai.getValor() + " é: " + pai.getFb());
    	if (pai.getFb() <= -2){
    		if (pai.getDireito().getFb() > 0){
    			System.out.println("RotacaoEsquerdaDupla em " + pai.getValor());
    			rotacaoEsquerdaDupla(pai);
    			return;
    		} else{
    			System.out.println("RotacaoEsquerdaSimples em " + pai.getValor());
    			rotacaoEsquerdaSimples(pai);
    			return;
    		}
    	} else if(pai.getFb() != 0){
    		ajustarFbInsercao(pai);  
    	}
    }
  }
  
  // ROTAÇÃO ESQUERDA SIMPLES
  public void rotacaoEsquerdaSimples(No pai){
	  No filho = pai.getDireito();
	  filho.setPai(pai.getPai());
	  
	  if (pai.getPai() != null){
		  filho.setPai(pai.getPai());
		  if (pai.getValor() < pai.getPai().getValor()){
			  pai.getPai().setEsquerdo(filho);
		  } else {
			  pai.getPai().setDireito(filho);
		  }
	  }
	  
	  if (filho.getEsquerdo() != null){
		filho.getEsquerdo().setPai(pai);
		pai.setDireito(filho.getEsquerdo());
	  }
	  
	  filho.setEsquerdo(pai);
	  pai.setPai(filho);
	  
	  System.out.println("ROTAÇÃO: antigo fb do pai (" + pai.getValor() + ") é: " + pai.getFb());
	  System.out.println("ROTAÇÃO: antigo fb do filho (" + filho.getValor() + ") é: " + filho.getFb());
	  
	  pai.setFb(pai.getFb() + 1 - Math.min(filho.getFb(), 0));
	  filho.setFb(filho.getFb() + 1 + Math.max(pai.getFb(), 0));
	  
	  System.out.println("ROTAÇÃO: novo fb do pai (" + pai.getValor() + ") é: " + pai.getFb());
	  System.out.println("ROTAÇÃO: novo fb do filho (" + filho.getValor() + ") é: " + filho.getFb());
  }
  
  // ROTAÇÃO DIREITA SIMPLES
  public void rotacaoDireitaSimples(No pai){
	  No filho = pai.getEsquerdo();
	  filho.setPai(pai.getPai());
	  
	  if (pai.getPai() != null){
		  if (pai.getValor() < pai.getPai().getValor()){
			  pai.getPai().setEsquerdo(filho);
		  } else {
			  pai.getPai().setDireito(filho);
		  }
	  }
	  
	  if (filho.getDireito() != null){
		  filho.getDireito().setPai(pai);
		  pai.setEsquerdo(filho.getDireito());
	  }
	  
	  filho.setDireito(pai);
	  pai.setPai(filho);
	  
	  System.out.println("ROTAÇÃO: antigo fb do pai (" + pai.getValor() + ") é: " + pai.getFb());
	  System.out.println("ROTAÇÃO: antigo fb do filho (" + filho.getValor() + ") é: " + filho.getFb());
	  
	  pai.setFb(pai.getFb() - 1 - Math.max(filho.getFb(), 0));
	  filho.setFb(filho.getFb() - 1 + Math.min(pai.getFb(), 0));
	  
	  System.out.println("ROTAÇÃO: novo fb do pai (" + pai.getValor() + ") é: " + pai.getFb());
	  System.out.println("ROTAÇÃO: novo fb do filho (" + filho.getValor() + ") é: " + filho.getFb());
	  
  }
  
  // ROTAÇÃO ESQUERDA DUPLA
  public void rotacaoEsquerdaDupla(No no){
	  rotacaoDireitaSimples(no.getDireito());
	  rotacaoEsquerdaSimples(no.getPai());
  }

  // ROTAÇÃO DIREITA DUPLA
  public void rotacaoDireitaDupla(No no){
	  rotacaoEsquerdaSimples(no.getEsquerdo());
	  rotacaoDireitaSimples(no.getPai());
  }
  
  // REMOVER ==============================================================
  public void remover(int valor){
      No aRemover = acharNo(raiz, valor);
      No pai = aRemover.getPai();
      
      if (aRemover != null){
		  /* Primeiro caso: aRemover nao tem filhos */
    	  if (aRemover.getEsquerdo() == null && aRemover.getDireito() == null){
    		  if (aRemover.getValor() == raiz.getValor()){
    			  raiz = null;
    		  } else {
    			  aRemover.setPai(null);
        		  if (aRemover.getValor() == pai.getEsquerdo().getValor()){
        			  pai.setEsquerdo(null);
        		  } else if (aRemover.getValor() == pai.getDireito().getValor()){
        			  pai.setDireito(null);
        		  }
    		  }
    	  }
		  /* Segundo caso: aRemover so tem o filho esquerdo */
    	  if (aRemover.getEsquerdo() != null && aRemover.getDireito() == null){
    		  if (aRemover.getValor() == raiz.getValor()){
    			  raiz = aRemover.getEsquerdo();
    			  aRemover.getEsquerdo().setPai(null);
    		  } else {
    			  aRemover.setPai(null);
    			  aRemover.getEsquerdo().setPai(pai);
    			  if (aRemover.getValor() == pai.getEsquerdo().getValor()){
        			  pai.setEsquerdo(aRemover.getEsquerdo());
        		  } else if (aRemover.getValor() == pai.getDireito().getValor()){
        			  pai.setDireito(aRemover.getEsquerdo());
        		  }
    		  }
    	  }
		  /* Terceiro caso: aRemover so tem o filho direito */
    	  if (aRemover.getEsquerdo() == null && aRemover.getDireito() != null){
    		  if (aRemover.getValor() == raiz.getValor()){
    			  raiz = aRemover.getEsquerdo();
    			  aRemover.getDireito().setPai(null);
    		  } else {
    			  aRemover.setPai(null);
    			  aRemover.getDireito().setPai(pai);
    			  if (aRemover.getValor() == pai.getEsquerdo().getValor()){
        			  pai.setEsquerdo(aRemover.getDireito());
        		  } else if (aRemover.getValor() == pai.getDireito().getValor()){
        			  pai.setDireito(aRemover.getDireito());
        		  }
    		  }
    	  }
		  /* Quarto caso: aRemover tem dois filhos */
    	  if (aRemover.getEsquerdo() != null && aRemover.getDireito() != null){
    		  
    	  }
      } else {
    	  System.out.println("valor a remover não encontrado");
      }
  }
  
  // VERIFICAR BALANCEAMENTO

  // ACAHAR NÓ
  public No acharNo(No no, int valor){
	  if(no.getValor() == valor){
		  return no;
	  } else if(no.getValor() < valor && no.getDireito() != null) {
		  return acharNo(no.getDireito(), valor);
	  } else if(no.getValor() > valor && no.getEsquerdo() != null){
		  return acharNo(no.getEsquerdo(), valor);
	  } else {
		  return null;
	  }
  }
  
  // SUCESSOR
  public No sucessor(No no){
	  No sucessor = no.getDireito();
	  while (sucessor.getEsquerdo() != null){
		  sucessor = sucessor.getEsquerdo();  
	  }
	  return sucessor;
  }

  // IN ORDER
  public void inOrder(No no){
	  if (no.getEsquerdo() != null){
		  inOrder(no.getEsquerdo());
	  }
	  System.out.println(no.getValor() + " (" + no.getFb() + ")");
	  if (no.getDireito() != null){
		  inOrder(no.getDireito());
	  }
  }

}
