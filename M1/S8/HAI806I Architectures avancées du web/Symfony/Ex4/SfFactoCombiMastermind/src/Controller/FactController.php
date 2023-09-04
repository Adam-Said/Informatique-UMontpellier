<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

function factorielle($n) {
    $result = 1;
    for ($i = 1; $i <= $n; $i++) {
        $result *= $i;
    }
    return $result;
}

function combinaison($number, $number2) {
    $result = 1;
    $result = factorielle($number) / (factorielle($number2) * factorielle($number - $number2));
    return $result;
}

class FactController extends AbstractController
{
    #[Route('/fact/{number}', name: 'app_fact')]
    public function index($number): Response
    {
        $result = factorielle($number);
        return new Response('Fact de ('.$number . ") = " . $result);
    }

    #[Route('/combi/{number}/{number2}', name: 'app_combi')]
    public function combi($number, $number2): Response
    {
        $result = combinaison($number, $number2);
        return new Response('Combinaison de ('.$number . ") et (" . $number2 . ") = " . $result);
    }

    #[Route('/', name: 'app')]
    public function factoCombi(Request $request): Response
    {
        $n = $request->query->get('n');
        $p = $request->query->get('p');
        $fact = $request->query->get('fact');

        //champs non vides
        if(empty($n) && empty($p) && empty($fact)){ 
            return $this->render('base.html.twig');
        }

        if(!empty($fact) && empty($n) && empty($p)){ //si seulement le champs fact est complété
            $result = factorielle($fact);
            return $this->render('factoCombi.html.twig', ['result' => $result]);
        }

        if(!empty($n) && !empty($p) && empty($fact)){ //si n < p
            $result = combinaison($n, $p);
            return $this->render('factoCombi.html.twig', ['result' => $result]);
        } else {
            return $this->render('base.html.twig');
        }
    }

}
?>