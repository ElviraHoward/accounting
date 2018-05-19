package accounting.controller.wizard;

import accounting.model.Operation;
import com.google.inject.AbstractModule;
/**
 * Created by Elvira on 17.05.2018.
 */
public class WizardModel extends AbstractModule {

    @Override
    protected void configure() {
        Operation model = new Operation();
        bind(Operation.class).toInstance(model);
    }
}
