package co.com.sofkau.entrenamiento.curso.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.values.Directriz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class DirectrizAgregadaAMentoria extends DomainEvent {
    private final MentoriaId mentoriaId;
    private final Directriz directriz;

    public DirectrizAgregadaAMentoria(MentoriaId mentoriaId, Directriz directriz) {
        super("co.com.sofkau.entrenamiento.DirectrizAgregadaAMentoria");
        this.mentoriaId = mentoriaId;
        this.directriz = directriz;
    }

    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public Directriz getDirectiz() {
        return directriz;
    }
}
