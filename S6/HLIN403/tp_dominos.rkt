;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname tp_dominos) (read-case-sensitive #t) (teachpacks ((lib "image.rkt" "teachpack" "2htdp") (lib "draw.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ((lib "image.rkt" "teachpack" "2htdp") (lib "draw.rkt" "teachpack" "htdp")) #f)))
;ex 1
(define (random_dom) (list (random 1 7) (random 1 7)))
(define (create_dom a b) (list a b))
(define (set1 d a) (list a (cadr d)))
(define (set2 d b) (list (car d) b))

(define (get_cotes d) (list (car d) (cadr d)))
(define (cote1 d) (car d))
(define (cote2 d) (cadr d))
 

;ex 2
(define (domino_n j n) (if (< n 2) (car j) (domino_n (cdr j) (- n 1))))
(define (prem_dom j) (if (null? j) '() (car j)))
(define (der_dom j) (domino_n j (nbr_dom j)))
(define (suite_jeu j) (if (null? j) '() (cdr j)))
(define (nbr_dom j) (if (null? j) 0 (+ 1 (nbr_dom (suite_jeu j)))))
(define (ajouter_nbr j d n)
  (cond ((< n 2) (cons d  j))
        ((equal? (der_dom j) (prem_dom j)) (list (prem_dom j) d))
        (else (cons (prem_dom j) (ajouter_nbr (suite_jeu j) d (- n 1))))))
               
(define (ajouter_fin j d) (ajouter_nbr j d (+ (nbr_dom j) 1)))
(define (ajouter_deb j d) (ajouter_nbr j d 1))



;ex 3
(define (est_double? d) (equal? (cote1 d) (cote2 d)))


;ex 4
(define (doubles jeu) (filter (lambda (d) (est_double? d)) jeu)) 


;ex 5
(define (retourner d) (list (cote2 d) (cote1 d)))


;ex 6
(define (peut-jouer? j e) 
  (if (or (> e 6) (< e 0)) #f
      (ormap (lambda (d) (or (= (cote1 d) e) (= (cote2 d) e))) j)))

(define (jouable? j d)
  (cond ((null? j) #f)
        ((= (ext_g j) (cote1 d)) #t)
        ((= (ext_g j) (cote2 d)) #t)
        ((= (ext_d j) (cote1 d)) #t)
        ((= (ext_d j) (cote2 d)) #t)
        (else #f)))

 
;ex 7
(define (extraire j e) 
  (cond ((or (> e 6) (< e 0)) void)
        ((null? j) '())
        ((or (= (cote1 (domino_n j 1)) e) (= (cote2 (domino_n j 1)) e)) (domino_n j 1))
        (else (extraire (suite_jeu j) e))))


;ex 8
(define (chaine_valide? j) (if (< (nbr_dom j) 2) #t
                               (if (= (cote2 (domino_n j 1)) (cote1 (domino_n j 2)))
                                   (chaine_valide? (suite_jeu j)) #f)))


;ex 9
(define (ext_g j) (cote1 (domino_n j 1)))
(define (ext_d j) (cote2 (domino_n j (nbr_dom j))))


;ex 10
(define (supprimer j d)
  (if (null? j) '()
      (if (equal? (prem_dom j) d) (suite_jeu j)
          (cons (prem_dom j) (supprimer (suite_jeu j) d)))))


;ex 11
(define (ajouter j d)
  (cond ((= (ext_g j) (cote2 d)) (ajouter_deb j d))
        ((= (ext_g j) (cote1 d)) (ajouter_deb j (retourner d)))
        ((= (ext_d j) (cote1 d)) (ajouter_fin j d))
        ((= (ext_d j) (cote2 d)) (ajouter_fin j (retourner d)))
        (else j)))


;ex 12
(define (pose li)
  (let* ((j (car li))
         (ch (cadr li))
         (g (ext_g ch))
         (d (ext_d ch)))
         (cond ((peut-jouer? j g) (list (supprimer j (extraire j g)) (ajouter ch (extraire j g))))
               ((peut-jouer? j d) (list (supprimer j (extraire j d)) (ajouter ch (extraire j d))))
               (else li))))

;4.1

;13
(define (dessiner-gros-point p)
  (draw-solid-disk p 2))

;14
(define (dessiner-rectangle p1 p2)
  (begin 
  (draw-solid-line p1 (make-posn (posn-x p2) (posn-y p1)))
  (draw-solid-line (make-posn (posn-x p2) (posn-y p1)) p2)
  (draw-solid-line p2 (make-posn (posn-x p1) (posn-y p2)))
  (draw-solid-line (make-posn (posn-x p1) (posn-y p2)) p1)))

;15
(define (dessiner-demi-dominos p n)
  (let* ((x (posn-x p))
         (y (posn-y p)))
    (begin (dessiner-rectangle (make-posn (- x 12) (- y 12)) (make-posn (+ x 12) (+ y 12)))
           (cond ((= n 1) (dessiner-gros-point p))
                 ((= n 2) (begin (dessiner-gros-point (make-posn (+ x 6) (+ y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (- y 6)))))
                 ((= n 3) (begin (dessiner-gros-point (make-posn (+ x 6) (+ y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (- y 6)))
                                 (dessiner-gros-point p)))
                 ((= n 4) (begin (dessiner-gros-point (make-posn (+ x 6) (+ y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (- y 6)))
                                 (dessiner-gros-point (make-posn (+ x 6) (- y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (+ y 6)))))
                 ((= n 5) (begin (dessiner-gros-point (make-posn (+ x 6) (+ y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (- y 6)))
                                 (dessiner-gros-point (make-posn (+ x 6) (- y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (+ y 6)))
                                 (dessiner-gros-point p)))
                 ((= n 6) (begin (dessiner-gros-point (make-posn (+ x 6) (+ y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (- y 6)))
                                 (dessiner-gros-point (make-posn (+ x 6) (- y 6)))
                                 (dessiner-gros-point (make-posn (- x 6) (+ y 6)))
                                 (dessiner-gros-point (make-posn (+ x 6) y))
                                 (dessiner-gros-point (make-posn (- x 6) y))))
                 (else void)
                 ))))

;16
(define (dessiner-dominos p d)
  (let* ((x (posn-x p))
         (y (posn-y p)))
    (begin (dessiner-demi-dominos p (cote1 d))
           (dessiner-demi-dominos (make-posn (+ x 24) y) (cote2 d)))))


;17
(define (dessiner-jeu-dominos j n)
  (letrec ((aux (lambda (j n cpt)
    (let* ((x (if (= n 1) (+ 25 (* 48 (quotient cpt 5))) (- 316 (* 48 (quotient cpt 5)))))
          (y (- 240 (* 25 (remainder cpt 5)))))
      (if (null? j) #t
      (begin (dessiner-dominos (make-posn x y) (car j))
             (aux (cdr j) n (+ 1 cpt))))))))
  (aux j n 0))) 

;18
(define (dessiner-chaine-dominos c)
  (letrec ((aux (lambda (c cpt)
                  (let* ((x (+ 15 (* 50 (remainder cpt 6))))
                         (y (+ 15 (* 30 (quotient cpt 6)))))
                  (if (null? c) #t
                      (begin (dessiner-dominos (make-posn x y) (car c))
                             (aux (cdr c) (+ 1 cpt))))))))
    (aux c 0)))

;19
(define (generer-jeu x)
  (if (= x 0) '()
      (cons (random_dom) (generer-jeu (- x 1)))))

(define (initChaine d)
  (list d)) 

;20
(define (debut-jeu j1 j2)
  (list (generer-jeu j1) (generer-jeu j2) (list (random_dom))))


;21 
(define (jouer jeu1 jeu2 chaine)
  (letrec ((aux (lambda (j1 j2 ch tour)
                  (let ((j (if (= tour 1) j1 j2)))
                    (cond ((null? j2) "Le joueur 1 n'a plus de domino, il remporte la partie !")
                          ((null? j2) "Le joueur 2 n'a plus de domino, il remporte la partie !")
                                                            
                          ((equal? (pose (list j ch)) (list j ch)) (if (= tour 1)
                               "Le joueur 1 ne peux plus jouer, le joueur 2 gagne"
                               "Le joueur 2 ne peux plus jouer, le joueur 1 gagne"))

                          (else (let ((new_j (car (pose (list j ch))))
                                (new_ch (cadr (pose (list j ch)))))
                                  (begin
                                    (sleep-for-a-while 1)
                                    (clear-all)
                                    (if (= tour 1)
                                        (begin
                                          (dessiner-jeu-dominos new_j 1)
                                           (dessiner-jeu-dominos j2 2))
                                        (begin
                                          (dessiner-jeu-dominos j1 1)
                                           (dessiner-jeu-dominos new_j 2)))
                                    (dessiner-chaine-dominos new_ch)
                                    (if (= tour 1) (aux new_j j2 new_ch 2) (aux j1 new_j new_ch 1))))))))))
    (aux jeu1 jeu2 chaine 1)))


;TESTS
(start 350 256)
(jouer (generer-jeu 7) (generer-jeu 7) (list (random_dom)))