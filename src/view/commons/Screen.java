package view.commons;

public abstract class Screen implements IScreen{

    public void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }
    public void await() {
        try {
            Thread.sleep(2000); // Pausa por 2 segundos (2000 milissegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
