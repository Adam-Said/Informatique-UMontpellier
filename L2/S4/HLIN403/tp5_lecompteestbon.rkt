;Définition

(define LVal '(1 2 3 4 5 6 7 8 9 10 25 50 75 100))
(define Op '(+ * - /))

(define make-cible
  (lambda (ls)
    (let ((len (length ls)))         
      (list-ref ls (random len)))))