#lang racket

;;#|
(if #t "=============" #f)
(if #t "Edzo 1 et 2 :" #f)
(< 10 100)
(and (< 55 100) (> 55 0))
(* 1 2 3)
(if (< 10 20) #t #f)
(< 10 20)
(if #t "=============" #f)

(if #t "Edzo 3 :" #f)
(+ (- 2 6) 10) ;; =6
(* (+ 2 5) (+ (- 0 3) 12)) ;; =63
(/ (/ 9 2) (/ 2 4)) ;; 9
(if #t "=============" #f)

(if #t "Edzo 4 :" #f)
(not (and (< 55 100) (> 55 0))) ;; =#f
(or (not (< 55 100)) (not (> 55 0))) ;; =#f
(if #t "=============" #f)

(if #t "Edzo 5 :" #f)
;; Car c'est comme ça, c'est de la logique (pouèt)
(if #t "=============" #f)

(if #t "Edzo 6 et 7 :" #f)
(define puis2 (lambda (x) (* x x)))
(puis2 3)
(puis2 -4)
(puis2 1)
(puis2 (puis2 2))
(puis2 (puis2 1))
(if #t "=============" #f)

(if #t "Edzo 8 et 9 :" #f)
(define puis4 (lambda (x)(puis2 (puis2 x))))
(puis4 10) ;; =10000
(if #t "=============" #f)

(if #t "Edzo 10 :" #f)

;;|#


