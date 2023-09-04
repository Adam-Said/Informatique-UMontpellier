<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Bundle\DoctrineBundle\Registry;
use Doctrine\Persistence\ManagerRegistry;
use App\Entity\Tournoi;
use App\Entity\Evenement;
use Symfony\Component\Form\Extension\Core\Type\{TextType,ButtonType,EmailType,HiddenType,PasswordType,TextareaType,SubmitType,NumberType,DateType,MoneyType,BirthdayType};


class TestController extends AbstractController
{
    #[Route('/test/{name}', name: 'app_test')]
    public function index($name): Response
    {
        return new Response('Bonjour '.$name);
    }

    #[Route('/tournoi', name: 'app_tournoi')]
    public function tournoi(ManagerRegistry $doctrine): Response
    {
        $events = $doctrine->getRepository(Evenement::class)->findAll();


        return $this->render('tournoi.html.twig', [
            'events' => $events,
        ]);
    }

    #[Route('/tournoi/newevt/{nom<[0-9a-zA-Z ]+>}', name: 'app_newevt')]
    public function newevt(ManagerRegistry $doctrine, $nom): Response
    {
        $event = new Evenement();
        $event->setNom($nom);

        $entityManager = $doctrine->getManager();
        $entityManager->persist($event);
        $entityManager->flush();


        return new Response('New event of name '. $nom . ' added to the DB !');
    }

    #[Route("/tournoi/newtnoi/{evtid<[0-9]+>}/{nom<[0-9a-zA-Z ]+>}/{desc?}", name:"app_newtnoi")]
    public function newtnoi(ManagerRegistry $doctrine, $evtid, $nom): Response
    {
        $event = $doctrine->getRepository(Evenement::class)->find($evtid);
        if (!$event) {
            throw $this->createNotFoundException(
                'No event found for id '.$evtid
            );
        }
        $newTournoi = new Tournoi();
        $newTournoi->setNom($nom);
        $event->addTournoi($newTournoi);

        $entityManager = $doctrine->getManager();
        $entityManager->persist($event);
        $entityManager->persist($newTournoi);
        $entityManager->flush();


        return new Response('New tournament of name '. $nom . ' added to the DB and to the event !');
    }

    #[Route("/tournoi/saisieTnoi/{evtid<[0-9]+>}", name:"saisieTnoi")]
    public function saisieTnoi($evtid): Response {
        $tnoi=new Tournoi();
        $tnoi->setNom("");
        $tnoi->setDescription(""); // saisie donc vide
        $form = $this->createFormBuilder($tnoi)
        ->add('nom', TextType::class)
        ->add('description', TextType::class)
        ->add('sauver', SubmitType::class, ['label' => 'Créer le tournoi !'])
        ->getForm(); // le formulaire est créé
        return $this->render('saisieTnoi.html.twig', [
        'form' => $form->createView()]);
    }

    #[Route('/login', name: 'app_login')]
    public function login(AuthenticationUtils $authenticationUtils): Response
    {
        $error = $authenticationUtils->getLastAuthenticationErro();
        $lastUsername = $authenticationUtils->getLastUsername();

        return $this->render('login/index.html.twig', [
            'controller_name' => 'LoginController',
            'last_username' => $lastUsername,
            'error' => $error,
        ]);
    }


    #[Route('/logout', name: 'app_logout', methods: ['GET'])]
    public function logout(): Response
    {
        throw new \Exception('Don\'t forget to activate logout in security.yaml') ;
    }
}
?>