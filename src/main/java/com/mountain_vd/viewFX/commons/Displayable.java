package com.mountain_vd.viewFX.commons;

/**
 * Interface que define o contrato para objetos que podem ser renderizados
 * ou exibidos na interface gráfica.
 * <p>
 * A implementação desta interface deve fornecer a lógica necessária
 * para preparar ou atualizar a exibição do componente na tela.
 * </p>
 */
public interface Displayable {

     /**
      * Realiza o processo de renderização ou atualização visual do componente.
      * Este método deve ser chamado sempre que for necessário garantir que
      * o estado visual esteja sincronizado com os dados internos.
      */
     void render();
}
