package fr.kybox.school;

import fr.kybox.school.model.RoleEnum;
import fr.kybox.school.model.entity.Course;
import fr.kybox.school.model.entity.Person;
import fr.kybox.school.model.entity.Role;
import fr.kybox.school.model.entity.Subject;
import fr.kybox.school.repository.CourseRepository;
import fr.kybox.school.repository.PersonRepository;
import fr.kybox.school.repository.RoleRepository;
import fr.kybox.school.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.time.Duration;
import java.util.Arrays;

@EnableWebFlux
@SpringBootApplication
public class SchoolApplication {

	@Bean
	public CommandLineRunner initDatabase(RoleRepository roleRepository, SubjectRepository subjectRepository,
										CourseRepository courseRepository, PersonRepository personRepository){
		return (args) -> {

			// Set role list
			roleRepository.saveAll(Arrays.asList(
					new Role(RoleEnum.STUDENT.toString()), // 1
					new Role(RoleEnum.TEACHER.toString())  // 2
			)).blockLast(Duration.ofSeconds(1));

			// Set subject list
			subjectRepository.saveAll(Arrays.asList(
					new Subject(null, "Mathématiques"),
					new Subject(null, "Philosophie"),
					new Subject(null, "Médical")
			)).blockLast(Duration.ofSeconds(1));

			// Set person list
			personRepository.saveAll(Arrays.asList(
					new Person("John", "123", 1L, "student1.jpg"),
					new Person( "Nicolas", "123", 1L, "student2.jpg"),
					new Person("Sarah", "123", 1L, "student3.jpg"),
					new Person("Julie", "123", 2L, 1L, "teacher_math.jpg"),
					new Person("Robert", "123", 2L, 2L, "teacher_philosophy.jpg"),
					new Person("Sophie", "123", 2L, 3L, "teacher_medical.jpg")
			)).blockLast(Duration.ofSeconds(1));

			// Set course list
			courseRepository.saveAll(Arrays.asList(
					new Course("Géométrie différentielle", """
							La géométrie différentielle se penche sur les propriétés géométriques des courbes, des surfaces et des variétés en utilisant des outils de calcul différentiel et intégral. Elle est fondamentale en physique théorique, en relativité générale, en géométrie riemannienne et en topologie.""",
							4L, 1L),
					new Course("Théorie des nombres", """
							Cette branche des mathématiques se concentre sur l'étude des propriétés et des comportements des nombres entiers. Elle explore des concepts tels que les nombres premiers, les congruences, les fractions continues et les équations diophantiennes. La théorie des nombres joue un rôle clé dans la cryptographie et la sécurité informatique.""",
							4L, 1L),
					new Course("Éthique et philosophie morale", """
									Cette spécialité se concentre sur l'étude des questions liées à la moralité, à l'éthique et à la philosophie morale. Les étudiants examinent des sujets tels que la théorie du bien et du mal, l'éthique normative (comme la déontologie, la conséquentialisme, etc.), la philosophie politique, les dilemmes éthiques et les questions morales contemporaines. L'objectif est de développer une compréhension approfondie des questions morales et de développer la capacité de réflexion éthique.""",
							5L, 2L),
					new Course("Épistémologie", """
							L'épistémologie se penche sur la nature de la connaissance, de la croyance et de la justification. Elle explore les questions telles que : Qu'est-ce que la connaissance ? Comment pouvons-nous justifier nos croyances ? Quelles sont les limites de la connaissance humaine ? Les étudiants en épistémologie examinent également les concepts de vérité, de croyance justifiée, de perception et d'autres questions liées à la théorie de la connaissance. Cette spécialité vise à approfondir la compréhension de la nature de la connaissance et de la rationalité.""",
							5L, 2L),
					new Course("Chirurgie Cardiothoracique", """
							La chirurgie cardiothoracique est une spécialité médicale qui se concentre sur le traitement chirurgical des affections du cœur, des poumons, de la trachée et de l'œsophage. Les chirurgiens cardiothoraciques réalisent des interventions complexes telles que les pontages coronariens, les transplantations cardiaques et pulmonaires, et la réparation de malformations congénitales. Ils jouent un rôle crucial dans le sauvetage de vies en traitant des maladies graves du système cardiovasculaire et respiratoire.""",
							6L, 3L),
					new Course("Neurologie", """
							La neurologie est une spécialité médicale qui se consacre à l'étude et au traitement des troubles du système nerveux, y compris le cerveau, la moelle épinière et les nerfs périphériques. Les neurologues diagnostiquent et gèrent une gamme variée de conditions, telles que les accidents vasculaires cérébraux, l'épilepsie, la sclérose en plaques et les maladies neurodégénératives comme la maladie d'Alzheimer. Ils utilisent des techniques avancées d'imagerie et de neurophysiologie pour comprendre et traiter ces troubles complexes. La neurologie joue un rôle crucial dans l'amélioration de la qualité de vie des patients atteints de maladies neurologiques.""",
							6L, 3L)
			)).blockLast(Duration.ofSeconds(1));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
}
