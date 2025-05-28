package controller.dataBase.repository;

import java.util.List;

/**
 * {@code IRepository} define um contrato genérico para operações básicas de persistência (CRUD - Create, Read, Update, Delete)
 * sobre qualquer tipo de entidade {@code T}. Além das operações CRUD, esta interface também
 * define métodos para carregar, salvar e limpar os dados do repositório, e para obter
 * a classe da entidade gerenciada.
 *
 * @param <T> o tipo de entidade que será manipulada pelo repositório.
 *           Este tipo geralmente estende {@link model.commons.Identifiable} para garantir
 *           que cada entidade tenha um identificador único.
 */
public interface IRepository<T> {

    /**
     * Retorna uma lista de todos os itens atualmente armazenados no repositório.
     * A lista retornada pode ser uma cópia ou uma referência direta, dependendo da implementação.
     *
     * @return uma {@link List} contendo todos os itens do repositório;
     *         retorna uma lista vazia se o repositório não contiver itens.
     */
    List<T> getItems();

    /**
     * Busca e retorna um item específico do repositório com base no seu identificador único.
     *
     * @param id o ID do item a ser recuperado.
     * @return o item correspondente ao ID fornecido, ou {@code null} se nenhum item
     *         com o ID especificado for encontrado no repositório.
     */
    T getItemById(int id);

    /**
     * Atualiza os dados de um item existente no repositório.
     * Se o item com o mesmo ID do {@code item} fornecido existir, seus dados
     * são substituídos. A implementação deve garantir que a atualização seja persistida.
     *
     * @param item o item com os dados atualizados a serem aplicados no repositório.
     *             O ID do item é usado para localizar a entidade a ser atualizada.
     * @throws RuntimeException se o item a ser atualizado não for encontrado no repositório (opcional, dependendo da implementação).
     */
    void update(T item);

    /**
     * Adiciona um novo item ao repositório.
     * A implementação é responsável por atribuir um ID único ao item (se aplicável)
     * e persistir o novo item.
     *
     * @param item o novo item a ser adicionado ao repositório.
     */
    void add(T item);

    /**
     * Remove um item específico do repositório.
     * A remoção é geralmente baseada no ID do item. A implementação deve garantir
     * que a remoção seja persistida.
     *
     * @param item o item a ser removido do repositório.
     *             A comparação para encontrar o item a ser removido é tipicamente feita pelo ID.
     */
    void delete(T item);

    /**
     * Persiste o estado atual do repositório em um meio de armazenamento externo (ex: arquivo JSON, banco de dados).
     * Este método é chamado para garantir que todas as alterações em memória sejam salvas.
     */
    void save();

    /**
     * Carrega os dados do repositório a partir de um meio de armazenamento externo.
     * Este método é geralmente chamado na inicialização do repositório para popular
     * seus dados em memória.
     */
    void load();

    /**
     * Retorna a classe ({@code Class<T>}) da entidade que este repositório gerencia.
     * Esta informação é frequentemente necessária para operações genéricas de
     * serialização/desserialização ou reflexão.
     *
     * @return A {@code Class} objeto para o tipo de entidade {@code T}.
     */
    Class<T> getItemClass();

    /**
     * Remove todos os itens do repositório, efetivamente limpando seu conteúdo em memória.
     * Dependendo da implementação, isso também pode resetar contadores de ID internos.
     * Este método geralmente não afeta o armazenamento persistente por si só, a menos que
     * seja seguido por uma chamada a {@link #save()}.
     */
    void clear();
}