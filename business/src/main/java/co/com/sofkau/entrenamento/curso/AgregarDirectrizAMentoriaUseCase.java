package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofkau.entrenamiento.curso.Curso;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizAMentoria;

public class AgregarDirectrizAMentoriaUseCase extends UseCase<RequestCommand<AgregarDirectrizAMentoria>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarDirectrizAMentoria> agregarDirectrizAMentoriaRequestCommand) {

        var command = agregarDirectrizAMentoriaRequestCommand.getCommand();
        var curso = Curso.from
                (command.getCursoId(), repository().getEventsBy(command.getCursoId().value()));

        curso.agregarDirectrizAMentoria(command.getMentoriaId(), command.getDirectriz());

        emit().onResponse(new ResponseEvents(curso.getUncommittedChanges()));

    }
}


