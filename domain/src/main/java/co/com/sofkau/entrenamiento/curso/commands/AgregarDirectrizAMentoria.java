package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.Directriz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectrizAMentoria extends Command {

    private final MentoriaId mentoriaId;
    private final Directriz directriz;

    public AgregarDirectrizAMentoria(MentoriaId mentoriaId, Directriz directriz) {
        this.mentoriaId = mentoriaId;
        this.directriz = directriz;
    }

    public MentoriaId getMentoriaId() { return mentoriaId;}

    public Directriz getDirectriz() { return directriz;}
}
