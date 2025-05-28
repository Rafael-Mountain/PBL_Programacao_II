package controller.action;

/**
 * Representa o resultado de uma ação executada.
 *
 * A classe `ActionResult` armazena informações sobre o sucesso ou falha de uma ação, juntamente com uma mensagem que pode fornecer mais detalhes sobre o resultado.
 * Esta classe é usada para retornar o resultado de ações executadas sobre um modelo, como a criação, atualização ou remoção de um objeto.
 */
public class ActionResult {

    private final boolean success;
    private final String message;

    /**
     * Constrói um novo `ActionResult` com o status de sucesso e uma mensagem.
     *
     * @param success O status da ação (verdadeiro se a ação foi bem-sucedida, falso caso contrário).
     * @param message Uma mensagem explicativa sobre o resultado da ação.
     */
    public ActionResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Retorna o status de sucesso da ação.
     *
     * @return `true` se a ação foi bem-sucedida, `false` caso contrário.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Retorna a mensagem explicativa sobre o resultado da ação.
     *
     * @return A mensagem associada ao resultado da ação.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retorna uma representação em formato de string do resultado da ação.
     *
     * A string contém o status de sucesso e a mensagem associada ao resultado da ação.
     *
     * @return Uma string representando o `ActionResult`.
     */
    @Override
    public String toString() {
        return "ActionResult{success=" + success + ", message='" + message + "'}";
    }
}
