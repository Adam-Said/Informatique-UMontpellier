<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\HttpFoundation\Session\SessionInterface;


class MastermindController extends AbstractController {

#[Route('/master', name: 'app_master')]
    public function mastermind(SessionInterface $session): Response
    {
        
        if(!$session->has('answer')){
            $session->set('answer', rand(1000,9999));
            $session->set('win', false);
            $session->set('numlist', array());
            $session->set('good', array());
            $session->set('bad', array());
        }
        else if (isset($_POST['number'])) {
            if($session->get('answer') == intval($_POST['number'])) {
                $session->set('win', true);
            }
            else {
                $newNum = intval($_POST['number']);
                $newList = $session->get('numlist');
                array_push($newList, $newNum);
                $session->set('numlist', $newList);
                $chosenArray = str_split($newNum);
                $answerArray = str_split($session->get('answer'));
            
                $wellPlaced = 0;
                $misplaced = 0;
            
                for ($i = 0; $i < count($chosenArray); $i++) {
                    if ($chosenArray[$i] == $answerArray[$i]) {
                        $wellPlaced++;
                    } elseif (in_array($chosenArray[$i], $answerArray)) {
                        $misplaced++;
                    }
                }

                $newGood = $session->get('good');
                array_push($newGood, $wellPlaced);
                $session->set('good', $newGood);

                $newBad = $session->get('bad');
                array_push($newBad, $misplaced);
                $session->set('bad', $newBad);
            }
        }
        return $this->render('mastermind.html.twig', [
            'win' => $session->get('win'),
            'answer' => $session->get('answer'),
            'numlist' => $session->get('numlist'),
            'good' => $session->get('good'),
            'bad' => $session->get('bad'),
        ]);
    
    }

    
    #[Route('/master/clear', name: 'app_master_clear')]
    function sessionClear(SessionInterface $session) : Response {
        $session->clear();
        return $this->render('mastermind.html.twig', [
            'win' => false,
            'numlist' => array(),
        ]);
    }
}

?>