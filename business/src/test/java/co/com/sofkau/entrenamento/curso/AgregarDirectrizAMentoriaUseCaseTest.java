package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.Mentoria;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizAMentoria;
import co.com.sofkau.entrenamiento.curso.commands.AgregarMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgregarDirectrizAMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizAMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void AgregarDirectrizAMentoriaUseCaseHappyPass(){
        //arrange
        var idCurso = "ddddd";
        CursoId coursoId = CursoId.of(idCurso);
        MentoriaId mentoriaId = MentoriaId.of("0002");
        Directriz directriz = new Directriz("Leer y completar todo el módulo DDD Táctico");

        var command = new AgregarDirectrizAMentoria(coursoId, mentoriaId, directriz );

        when(repository.getEventsBy(idCurso)).thenReturn(history());
        useCase.addRepository(repository);
        //act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getMentoriaId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (DirectrizAgregadaAMentoria)events.get(0);
        Assertions.assertEquals("Leer y completar todo el módulo DDD Táctico", event.getDirectiz().value());

    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("Curso complementario para el training");
        var event = new CursoCreado(
                nombre,
                descripcion
        );
        event.setAggregateRootId("ddddd");

        MentoriaId mentoriaId = MentoriaId.of("0002");
        Nombre nombreMentoria = new Nombre("Completar DDD Táctico");
        Fecha fechaMentoria = new Fecha(LocalDateTime.now(), LocalDate.now());
        var eventMentoria = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fechaMentoria
        );

        eventMentoria.setAggregateRootId("ddddd");
        return List.of(event, eventMentoria);
    }

}