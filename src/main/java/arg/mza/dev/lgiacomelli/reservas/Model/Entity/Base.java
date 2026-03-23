package arg.mza.dev.lgiacomelli.reservas.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Base  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default true")
    private Boolean estado;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
