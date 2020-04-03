package com.pfa.microserviceoffers;

import com.pfa.microserviceoffers.models.Competence;
import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.Organisme;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import com.pfa.microserviceoffers.repositories.CompetenceRepository;
import com.pfa.microserviceoffers.repositories.OffreRepository;
import com.pfa.microserviceoffers.repositories.OrganismeRepository;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableFeignClients("com.pfa.microserviceoffers")
public class MicroserviceOffersApplication implements CommandLineRunner {

	@Autowired
	private OffreRepository offreRepository;

	@Autowired
	private OrganismeRepository organismeRepository;

	@Autowired
	private CompetenceRepository competenceRepository;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOffersApplication.class, args);
	}
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				connector.setProperty("relaxedQueryChars", "é");
				connector.setAttribute("relaxedPathChars", "é");
			}
		});
		return factory;
	}
	@Override
	public void run(String... args) throws Exception {

		Organisme o1=organismeRepository.save(new Organisme("Poulina","Agro-alimentaire"));
		Organisme o2=organismeRepository.save(new Organisme("Mind Engineering","Software engineering"));
		Organisme o3=organismeRepository.save(new Organisme("Sofrecom","Devops"));

		Competence java=this.competenceRepository.save(new Competence("Java"));
		Competence angular=this.competenceRepository.save(new Competence("Angular"));
		Competence web=this.competenceRepository.save(new Competence("FullStack"));
		Competence design=this.competenceRepository.save(new Competence("Adobe Creator"));
		Competence conception=this.competenceRepository.save(new Competence("Conception UML"));
		Competence scrum=this.competenceRepository.save(new Competence("Scrum"));
		Competence git=this.competenceRepository.save(new Competence("Version de control GIT"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


		HashSet hashSet=new HashSet();
		hashSet.add(git);
		hashSet.add(java);
		hashSet.add(scrum);
		hashSet.add(conception);
		System.out.println(hashSet.size());
		Offre of1=new Offre("Développeur JAVA H/F",
				LocalDateTime.parse("2020-03-31 14:25:15",formatter),
				LocalDateTime.parse("2020-07-04 14:25:15",formatter)
				,"Développeur JAVA","Tunis", TypeOffre.CDD,
				"Cette offre nécessite l'expérience en JAVA",2, Niveau.Licence,
				true, (long) 2,o1, hashSet);
		this.offreRepository.save(of1);


		hashSet.remove(java);
		hashSet.add(angular);
		System.out.println(hashSet.size());
		Offre of2=new Offre("Développeur Angular 5 H/F",
				LocalDateTime.parse("2020-03-30 14:25:15",formatter),
				LocalDateTime.now()
				,"Développeur Angular","Ariana", TypeOffre.TempsPartiel,
				"Cette offre nécessite l'expérience en Angular",1, Niveau.Mastere,
				true, (long) 2,o2, hashSet);
		this.offreRepository.save(of2);


		hashSet.add(java);
		hashSet.add(web);
		Offre of3=new Offre("Développeur Fullstack H/F",
				LocalDateTime.parse("2020-04-10 14:25:15",formatter),
				LocalDateTime.parse("2020-05-21 14:25:15",formatter)
				,"Développeur Fullstack Spring boot et Angular 5+","Mannouba", TypeOffre.TempsPlein,
				"Cette offre nécessite l'expérience en JAVA Spring boot et Angular",4, Niveau.Ingenieur,
				true, (long) 2,o3,hashSet);
		this.offreRepository.save(of3);


		hashSet.clear();
		hashSet.add(design);
		System.out.println(hashSet.size());
		Offre of4=new Offre("Designer H/F",
				LocalDateTime.parse("2020-04-15 14:25:15",formatter),
				LocalDateTime.parse("2020-04-29 14:25:15",formatter)
				,"Web and mobile Designer","Ben Arous", TypeOffre.TravailTemporaire,
				"Cette offre nécessite l'expérience en Photoshop et Adobe ...",3, Niveau.Autre,
				true, (long) 2,o1, hashSet);
		this.offreRepository.save(of4);
	}
}
