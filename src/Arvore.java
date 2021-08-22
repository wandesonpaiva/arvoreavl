public class Arvore {

  public No raiz;

  // INSERIR
  public void inserir(int valor) {
    No no = new No(valor);
    if (raiz == null) {
    	System.out.println(valor + " inserido na raiz");
    	raiz = no;
    } else {
    	No pai = acharPai(valor, raiz);
		no.setPai(pai);
		if (pai.getValor() > valor) {
			System.out.println(valor + " inserido a esquerda de " + pai.getValor());
			pai.setEsquerdo(no);
		} else if(pai.getValor() < valor) {
			System.out.println(valor + " inserido a direita de " + pai.getValor());
			pai.setDireito(no);
		}
		ajustarFb(no.getValor(), pai, 1);
    }
  }

  // ACHAR PAI
  public No acharPai(int valor, No no){
    if (valor < no.getValor() && no.getEsquerdo() != null) {
      return acharPai(valor, no.getEsquerdo());
    } else if(valor > no.getValor() && no.getDireito() != null) {
      return acharPai(valor, no.getDireito());
    } else {
      return no;
    }
  }

  // flag =  1 => insercao
	// flag = -1 => remoção
	public void ajustarFb(int valor, No pai, int flag) {
		if (pai != null) {
			if(flag == 1) {
				if (valor > pai.getValor()) {
					pai.setFb(pai.getFb() - 1);
					if (pai.getFb() < -1) {
						if(pai.getDireito() != null && pai.getDireito().getFb() > 0) {
							System.out.println("rotacao esquerda dupla em " + pai.getValor());
							pai = rotacaoEsquerdaDupla(pai);
						} else if(pai.getDireito() != null && pai.getDireito().getFb() <= 0) {
							System.out.println("rotacao esquerda simples em " + pai.getValor());
							pai = rotacaoEsquerdaSimples(pai);
						}
					}
					if (pai.getPai() != null && pai.getFb() != 0) {
						ajustarFb(pai.getValor(), pai.getPai(), flag);
					}
				} else if (valor < pai.getValor()) {
					pai.setFb(pai.getFb() + 1);
					if (pai.getFb() > 1) {
						if(pai.getEsquerdo() != null && pai.getEsquerdo().getFb() < 0) {
							System.out.println("rotacao direita dupla em " + pai.getValor());
							pai = rotacaoDireitaDupla(pai);
						} else if(pai.getEsquerdo() != null && pai.getEsquerdo().getFb() >= 0) {
							System.out.println("Teste rotacao direita simples em " + pai.getValor() + "(" + pai.getFb() + ")" );
							pai = rotacaoDireitaSimples(pai);
						}
					}
					if (pai.getPai() != null && pai.getFb() != 0) {
						ajustarFb(pai.getValor(), pai.getPai(), flag);
					}
				}
			} else if(flag == -1) {
				if (valor > pai.getValor()) {
					pai.setFb(pai.getFb() + 1);
					if (pai.getFb() > 1) {
						if(pai.getEsquerdo() != null && pai.getEsquerdo().getFb() < 0) {
							System.out.println("rotacao direita dupla em " + pai.getValor());
							pai = rotacaoDireitaDupla(pai);
						} else {
							System.out.println("rotacao direita simples em " + pai.getValor());
							pai = rotacaoDireitaSimples(pai);
						}
					}
					if (pai.getPai() != null && pai.getFb() == 0) {
						ajustarFb(pai.getValor(), pai.getPai(), flag);
					}
				} else {
					pai.setFb(pai.getFb() - 1);
					if (pai.getFb() < -1) {
						if(pai.getDireito() != null && pai.getDireito().getFb() > 0) {
							System.out.println("rotacao esquerda dupla em " + pai.getValor());
							pai = rotacaoEsquerdaDupla(pai);
						} else {
							System.out.println("rotacao esquerda simples em " + pai.getValor());
							pai = rotacaoEsquerdaSimples(pai);
						}
					}
					if (pai.getPai() != null && pai.getFb() == 0) {
						ajustarFb(pai.getValor(), pai.getPai(), flag);
					}
				}
			}
		}
		System.out.println("No:" + pai.getValor() + "Fb: " + pai.getFb());
	}
  
  // ROTAÇÃO ESQUERDA SIMPLES
  public No rotacaoEsquerdaSimples(No inicial) {
	  No direito = inicial.getDireito();
	  direito.setPai(inicial.getPai());
	  inicial.setDireito(direito.getEsquerdo());
	  
	  if (inicial.getDireito() != null) {
		  inicial.getDireito().setPai(inicial);
	  }
	  
	  direito.setEsquerdo(inicial);
	  inicial.setPai(direito);
	  
	  if (direito.getPai() != null) { 
		  if (direito.getPai().getDireito() == inicial) {
			  direito.getPai().setDireito(direito);
		  }
		  else if (direito.getPai().getEsquerdo() == inicial) {
			  direito.getPai().setEsquerdo(direito);
		  }
	  }
	  
	  if (inicial.getValor() == this.raiz.getValor()) {
		  raiz = direito;
	  }
	  
	  inicial.setFb(inicial.getFb() + 1 - Math.min(direito.getFb(), 0));
	  direito.setFb(direito.getFb() + 1 + Math.max(inicial.getFb(), 0));
	  
	  return direito;
  }
  
  // ROTAÇÃO DIREITA SIMPLES
  public No rotacaoDireitaSimples(No inicial) {
	  No esquerdo = inicial.getEsquerdo();
	  esquerdo.setPai(inicial.getPai());
	  inicial.setEsquerdo(inicial.getDireito());
	  
	  if(inicial.getEsquerdo() != null) {
		  inicial.getEsquerdo().setPai(inicial);
	  }
	  
	  esquerdo.setDireito(inicial);
	  inicial.setPai(esquerdo);
	  
	  if (esquerdo.getPai() != null) { 
		  if (esquerdo.getPai().getDireito() == inicial) {
			  esquerdo.getPai().setDireito(esquerdo);
		  } 
		  else if (esquerdo.getPai().getEsquerdo() == inicial) {
			  esquerdo.getPai().setEsquerdo(esquerdo);
		  }
	  }
	  
	  if (inicial.getValor() == this.raiz.getValor()) {
		  raiz = esquerdo;
	  }
	  
	  inicial.setFb(inicial.getFb() - 1 - Math.max(esquerdo.getFb(), 0));
	  esquerdo.setFb(esquerdo.getFb() - 1 + Math.min(inicial.getFb(), 0));
	  return esquerdo;
  }
  
  // ROTAÇÃO ESQUERDA DUPLA
  public No rotacaoEsquerdaDupla(No no) {
	  rotacaoDireitaSimples(no.getDireito());
	  return rotacaoEsquerdaSimples(no);
  }

  // ROTAÇÃO DIREITA DUPLA
  public No rotacaoDireitaDupla(No no) {
	  rotacaoEsquerdaSimples(no.getEsquerdo());
	  return rotacaoDireitaSimples(no);
  }
  
  // REMOVER
  public void remover(int valor) {
    No aRemover = acharNo(raiz, valor);
    No pai = aRemover.getPai();
      
    if (aRemover != null) {
      /* Primeiro caso: aRemover nao tem filhos */
      if (aRemover.getEsquerdo() == null && aRemover.getDireito() == null) {
        if (aRemover.getValor() == raiz.getValor()) {
          raiz = null;
        } else {
          aRemover.setPai(null);
          if (aRemover.getValor() == pai.getEsquerdo().getValor()) {
            pai.setEsquerdo(null);
          } else if (aRemover.getValor() == pai.getDireito().getValor()) {
            pai.setDireito(null);
          }
        }
      }
		  /* Segundo caso: aRemover so tem o filho esquerdo */
      if (aRemover.getEsquerdo() != null && aRemover.getDireito() == null) {
        if (aRemover.getValor() == raiz.getValor()) {
          raiz = aRemover.getEsquerdo();
          aRemover.getEsquerdo().setPai(null);
        } else {
          aRemover.setPai(null);
          aRemover.getEsquerdo().setPai(pai);
          if (aRemover.getValor() == pai.getEsquerdo().getValor()) {
            pai.setEsquerdo(aRemover.getEsquerdo());
          } else if (aRemover.getValor() == pai.getDireito().getValor()) {
            pai.setDireito(aRemover.getEsquerdo());
          }
        }
      }
		  /* Terceiro caso: aRemover so tem o filho direito */
      if (aRemover.getEsquerdo() == null && aRemover.getDireito() != null) {
        if (aRemover.getValor() == raiz.getValor()) {
          raiz = aRemover.getEsquerdo();
          aRemover.getDireito().setPai(null);
        } else {
          aRemover.setPai(null);
          aRemover.getDireito().setPai(pai);
          if (aRemover.getValor() == pai.getEsquerdo().getValor()) {
            pai.setEsquerdo(aRemover.getDireito());
          } else if (aRemover.getValor() == pai.getDireito().getValor()) {
            pai.setDireito(aRemover.getDireito());
          }
        }
      }
		  /* Quarto caso: aRemover tem dois filhos */
      if (aRemover.getEsquerdo() != null && aRemover.getDireito() != null) {
        // remover sucessor, pegar chave dele e atribuir a aremover
        No sucessor = sucessor(aRemover);
        int aux_valor = sucessor.getValor();
        remover(aux_valor);
        aRemover.setValor(aux_valor);
      }
    } else {
      System.out.println("valor a remover não encontrado");
    }
  }

  // ACAHAR NÓ
  public No acharNo(No no, int valor) {
	  if(no.getValor() == valor) {
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
  public No sucessor(No no) {
    No sucessor = no.getDireito();
    while (sucessor.getEsquerdo() != null){
      sucessor = sucessor.getEsquerdo();  
    }
    return sucessor;
  }

  // IN ORDER
  public void inOrder(No no) {
    if (no.getEsquerdo() != null) {
      inOrder(no.getEsquerdo());
    }
    System.out.println(no.getValor() + " (" + no.getFb() + ")");
    if (no.getDireito() != null) {
      inOrder(no.getDireito());
    }
  }
}
