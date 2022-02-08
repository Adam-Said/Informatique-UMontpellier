#lang racket
(define (von_koch prof a) (if (= prof 0) a (von_koch (- prof 1) (* (/ 4 3) a))))

(require (lib "turtles.ss" "graphics"))

(define (spirale x deg av)
  (if (< x 1) (turn 0) ((turn deg)
                        (draw av)
                        (spirale (- x 1) deg (+ av 1))))
)


(define (von_graphic prof a trait) (if (= prof 0) (void) (begin
                                                                (if (not (= prof 1)) (von_graphic (- prof 1) a (/ trait 3)) (draw (/ trait 3))) (begin
                                                                (turn 60)
                                                                (if (not (= prof 1)) (von_graphic (- prof 1) a (/ trait 3)) (draw (/ trait 3))) (begin
                                                                (turn 240)
                                                                (if (not (= prof 1)) (von_graphic (- prof 1) a (/ trait 3)) (draw (/ trait 3))) (begin
                                                                (turn 60)
                                                                (if (not (= prof 1)) (von_graphic (- prof 1) a (/ trait 3)) (draw (/ trait 3)))))))))
                                                             


                                 
(define (von_g prof a) (begin
                          (move (/ a 2))
                          (turn 180)
                          (move a)
                          (turn 180)
                          (von_graphic prof a a)
  ))


(define (floc prof a) (begin
                        (turtles)
                        (clear)
                        (von_g prof a)
                        (turn 240)
                        (move (/ a 2))
                        (von_g prof a)
                        (turn 240)
                        (move (/ a 2))
                        (von_g prof a)
                       ))

;;Exercice 7
(define (fact-accu x acc) (if (= x 0) acc (fact-accu (- x 1) (* acc x)))) 
(define (facto x) (fact-accu x 1))

(define (somm-accu x acc) (if (= x 0) acc (somm-accu (- x 1) (+ acc x)))) 
(define (somm x) (somm-accu x 0))

(define (liste-accu x acc) (if (= x 0) acc (liste-accu (- x 1) (cons x acc)))) 
(define (liste x) (liste-accu x (list)))


;;Exercice 8
(define term(lambda (x y acc) (if (= (- x 1) y) acc (term(* 1 x)(- y 1)(+ acc (* y y))))))
(define (e x y) (term x y 0))

(define (factorielleB m n acc) (if (= n m) acc (factorielleB(* 1 m)(- n 1)(* acc n))))
(define (f1 m n) (factorielleB m n 1))

(define facB(lambda (y x acc) (if (= x y) acc (facB(* 1 y)(- x 1)(* acc x)))))
(define (g1 y x) (facB y x 1))

(define somB(lambda (y x acc) (if (= x y) acc (somB(* 1 y)(- x 1)(+ acc x)))))
(define (h1 y x) (somB y x 0))




