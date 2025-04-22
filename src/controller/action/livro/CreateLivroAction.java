package controller.action.livro;

import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import controller.dataBase.LivroRepository;
import model.Livro;

public class CreateLivroAction extends BaseAction<Livro> {
    public CreateLivroAction(Validation<Livro> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Livro livro) {
        if (!isValid(livro)) {
            return new ActionResult(false, getErrorMessage());
        }

        LivroRepository.getInstance().save(livro);
        return new ActionResult(true,"Livro criado com sucesso");
    }
}
