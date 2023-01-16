; TP1 LISP
; Adam Said

(print "Ex1. lambda-expressions")
(princ ((lambda (x) (+ (* 2 x) 3)) 4))
(print ((lambda (x y) (* (+ x 2) (+ y 6))) 7 8))
(print ((lambda (v) ((lambda (x) (* 3 x)) (+ v 2))) 8))
(print ((lambda (v) ((lambda (x) (* v x)) (+ v 2))) 8))
(print ((lambda (v) ((lambda (v) (* 3 v)) (+ v 2))) 8))
(print ((lambda (x y z) (+ (* x y) (* y z))) 2 3 4))
(print ((lambda (x y) (* x x y y)) 4 5))
(print ((lambda (x) (* x x 2)) 4))
(print ((lambda (x) (* x x 2)) 4))

(print "Ex2. fonctions globales")
(defun f (x) (+ 3 x))
(defun g (v) (* 5 (f (+ v 2))))

(print (g 8))

; (defun f (x) (+ v x)) ne peut pas fonctionner car v n'est pas défini

(defun fac (n) (if (= n 0) 1 (* n (fac (- n 1)))))

(print "fac 5 = ")
(princ (fac 5))

(defun fibo (n) (if (= n 1) 1 (if (= n 0) 0 (+ (fibo (- n 1)) (fibo (- n 2))))))
(print "fibo 15 = ")
(princ (fibo 15))

(print "Ex3. les listes et les cellules")

(print (car '()))
(print (car '(())))
(print (cdr '()))
(print (eq '(()) '((()))))
(print (equal '(()) '((()))))
;(print (= '(()) '((()))))

(defun countList (l) (if (null l) 0 (+ 1 (countList (cdr l)))))
(print "countList '(1 2 3 4 5) = ")
(princ (countList '(1 2 3 4 5)))

(print "countList '(1 (2 3) 4) = ")
(princ (countList '(1 (2 3) 4)))

(print "countList '(1 (2) (3) 4) = ")
(princ (countList '(1 (2) (3) 4)))

(defun invList (l) (if (null l) '() (append (invList (cdr l)) (list (car l)))))
(print "invList '(1 2 3 4 5) = ")
(princ (invList '(1 2 3 4 5)))

(print "invList '(1 (2 3) 4) = ")
(princ (invList '(1 (2 3) 4)))

(print "invList '(1 (2) (3) 4) = ")
(princ (invList '(1 (2) (3) 4)))

(print "Ex4. fonctions sur les listes plates")

(defun newmember (x l) (if(eq l '()) '() (if(eq x (car l)) l (member x (cdr l)))))
(print "newmember 3 '(1 2 3 4 5) = ")
(princ (newmember 3 '(1 2 3 4 5)))

(defun newlength (l) (if (null l) 0 (+ 1 (countList (cdr l)))))
(print "newlength '(1 2 3 4 5) = ")
(princ (newlength '(1 2 3 4 5)))

(defun newlast (l) (if (null l) '() (if (null (cdr l)) (car l) (last (cdr l)))))
(print "newlast '(1 2 3 4 5) = ")
(princ (newlast '(1 2 3 4 5)))

(defun makelist (n) (if (= n 0) '() (cons n (makelist (- n 1)))))
(print "makelist 5 = ")
(princ (makelist 5))

(defun makelistasc (n) (if (= n 0) '() (append (makelistasc (- n 1)) (list n))))
(print "makelistasc 5 = ")
(princ (makelistasc 5))

(defun copylist (l) (if (null l) '() (if (integerp (car l)) (cons (car l) (copylist (cdr l))) (copylist (cdr l)))))
(print "copylist '(1 2 3 4 5) = ")
(princ (copylist '(1 2 3 4 5)))

(print "copylist '(1 2 (3 4) 5) = ")
(princ (copylist '(1 2 (3 4) 5)))

(defun newremove (x l) (if (null l) '() (if (eq x (car l)) (remove x (cdr l)) (cons (car l) (remove x (cdr l))))))
(print "newremove 3 '(1 2 3 4 5) = ")
(princ (newremove 3 '(1 2 3 4 5)))

(defun newappend (l1 l2) (if (null l1) l2 (cons (car l1) (append (cdr l1) l2))))
(print "newappend '(1 2 3) '(4 5 6) = ")
(princ (newappend '(1 2 3) '(4 5 6)))

(defun newadjoin (x l) (if (member x l) l (cons x l)))
(print "newadjoin 6 '(1 2 3 4 5) = ")
(princ (newadjoin 6 '(1 2 3 4 5)))

(print "Ex5. fonctions sur les arbres binaires")

(defun sizetree (tree)
    (if (equal tree nil) 
        0
        (if (integerp (car tree))
            (+ 1 (sizetree (cdr tree)))
            (+  (sizetree (car tree))
                (sizetree (cdr tree))))))
(print "sizetree '(1 2 3 4 5) = ")
(princ (sizetree '(1 2 3 4 5)))
(print "sizetree '((1 (2 (5 (6)))) (3 (4 (7)))) = ")
(princ (sizetree '((1 (2 (5 (6)))) (3 (4 (7))))))

(defun copyTree (tree)
    (if (equal tree nil) 
        nil
        (if (integerp (car tree))
            (cons (car tree) (copyTree (cdr tree)))
            (cons (copyTree (car tree))
                  (copyTree (cdr tree))))))
(print "copyTree '((1 (2 (5 (6)))) (3 (4 (7)))) = ")
(princ (copyTree '((1 (2 (5 (6)))) (3 (4 (7))))))

(defun subsTree (x y tree) 
    (if (equal tree nil) 
        nil
        (if (integerp (car tree))
            (if (eq (car tree) x)
                (cons y (subsTree x y (cdr tree)))
                (cons (car tree) (subsTree x y (cdr tree))))
            (cons (subsTree x y (car tree))
                  (subsTree x y (cdr tree))))))

(print "subsTree 2 8 '((1 (2 (5 (6)))) (3 (4 (7)))) = ")
(princ (subsTree 2 8 '((1 (2 (5 (6)))) (3 (4 (7))))))