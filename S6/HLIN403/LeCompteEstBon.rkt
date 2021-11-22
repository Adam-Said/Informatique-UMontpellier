#lang racket
;vals de base
(define LVal '(1 2 3 4 5 6 7 8 9 10 25 50 75 100))
(define Op (list + * - /))

;tirages
(define (make-cible) (+ 100 (random 900)))

(define (tirage L cpt)
  (cond ((= cpt 0) (car L))
        (else (tirage (cdr L) (- cpt 1)))))

(define (make-tirage) (list (tirage LVal (random 14)) (tirage LVal (random 14)) (tirage LVal (random 14)) (tirage LVal (random 14)) (tirage LVal (random 14)) (tirage LVal (random 14))))

(define (estDans? e l) (cond ((null? l) #f)
                             ((= e (car l)) #t)
                             (else (estDans? e (cdr l)))))

(define (estValide? op v1 v2)
  (if (= v2 0) #f (if (> (op v1 v2) -1) (if (equal? op /) (= (quotient v1 v2) (/ v1 v2)) #t) #f)))



(define (opere ops v1 v2)
  (cond ((null? ops) '())
        ((estValide? (car ops) v1 v2) (cons ((car ops) v1 v2) (opere (cdr ops) v1 v2)))
        ((estValide? (car ops) v2 v1) (cons ((car ops) v2 v1) (opere (cdr ops) v1 v2)))
        (else '())
        ))



(define (plaq o l av)
  (if (null? o) '()
      (cons (append av (list (car o)) l)
            (plaq av (cdr o) l))))


(define (plaqs op v l av)
  (if (null? l) '()
    (cons (plaq (opere op v (car l)) (cdr l) av)
          (plaqs op v (cdr l) (cons (car l) av)))))


(define (genere_plaque op L)
  (letrec ((aux (lambda (ops l av)
  (cond ((null? l) '())
        ((null? (cdr l)) '())
        (else (cons (plaqs ops (car l) (cdr l) av)
                    (aux ops (cdr l) (cons (car l) av)))
    )))))
    (aux op L '())))




;tests
(define F1 (make-tirage))
(define F2 (make-cible))
(display "tirage plaques : ") F1
(display "Tirage cible : ") (make-cible)
(display "estDans? 4 (1 2 3 4 5) : ") (estDans? 4 '(1 2 3 4 5))
(display "estValide? 1/5 : ") (estValide? / 1 5)
(display "Liste des opérations pour 1 et 5 : ") (opere Op 1 5)
(display "\n") (genere_plaque Op F1)
