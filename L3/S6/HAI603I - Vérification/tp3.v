Require Import ListSet.

(* Exercice 1 *)
Print mult.
Print Nat.mul.

Fixpoint mult (n m : nat) {struct n} : nat :=
  match n with
  | 0 => 0
  | S p => (plus (mult p m) m)
  end.

Print mult.

Lemma ex1_1 : forall n : nat, (mult 2 n) = (plus n n).

Proof.
  intro.
  simpl.
  reflexivity.
Qed.

Lemma com : forall n m : nat, (plus n m) = (plus m n).

Proof.
  intros.
  elim n.
  elim m.
  reflexivity.
  intros.
  simpl.
  rewrite <- H.
  simpl.
  reflexivity.
  intros.
  simpl.
  rewrite H.
  elim m.
  simpl.
  reflexivity.
  intros.
  simpl.
  rewrite H0.
  reflexivity.
Qed.

Lemma ex1_2 : forall n : nat, (mult n 2) = (plus n n).

Proof.
  intro.
  elim n.
  simpl.
  reflexivity.
  intros.
  simpl.
  rewrite H.
  rewrite com.
  simpl.
  rewrite (com n0 (S n0)).
  simpl.
  reflexivity.
Qed.

(* Exercice 2 *)
Open Scope list.
Print app.

Parameters A : Type.

Fixpoint rev (l : list A) {struct l} : list A :=
  match l with
  | nil => nil
  | a :: l1 => (app (rev l1) (a :: nil))
  end.

Lemma ex2_1 : forall l : list A, forall e : A,
(rev (app l (e :: nil))) = (e :: (rev l)).

Proof.
  intros.
  elim l.
  simpl.
  reflexivity.
  intros.
  simpl.
  rewrite H.
  reflexivity.
Qed.

Lemma ex2_2 : forall l : list A, (rev (rev l)) = l.

Proof.
  intros.
  elim l.
  simpl.
  reflexivity.
  intros.
  simpl.
  rewrite ex2_1.
  rewrite H.
  reflexivity.
Qed.

(* Exercice 3 *)
Parameters S : Set.

Inductive FProp : Set :=
  | Symb : S -> FProp
  | Not : FProp -> FProp
  | And : FProp -> FProp -> FProp
  | Or : FProp -> FProp -> FProp
  | Impl : FProp -> FProp -> FProp
  | Equ : FProp -> FProp -> FProp.

Print ListSet.

Fixpoint sub (f : FProp) {struct f} : list FProp := 
  match f with
  | (Symb s) => (Symb s) :: nil
  | (Not f1) => (Not f1) :: (sub f1)
  | (Or f1 f2) => (Or f1 f2) :: (app (sub f1) (sub f2))
  | (And f1 f2) => (And f1 f2) :: (app (sub f1) (sub f2))
  | (Impl f1 f2) => (Impl f1 f2) :: (app (sub f1) (sub f2))
  | (Equ f1 f2) => (Equ f1 f2) :: (app (sub f1) (sub f2))
  end.

Fixpoint nbc (f : FProp) {struct f} : nat :=
  match f with 
  | (Symb s) => 0
  | (Not f1) => 1 + (nbc f1)
  | (Or f1 f2) => 1 + (nbc f1) + (nbc f2)
  | (And f1 f2) => 1 + (nbc f1) + (nbc f2)
  | (Impl f1 f2) => 1 + (nbc f1) + (nbc f2)
  | (Equ f1 f2) => 1 + (nbc f1) + (nbc f2)
  end.

Parameters a b c : S.

Theorem test1 : (nbc (Symb a)) = 0.

Proof.
  simpl.
  reflexivity.
Qed.

Theorem test2 : (nbc (And (Or (Symb a) (Symb b)) (Symb c))) = 2.

Proof.
  simpl.
  reflexivity.
Qed.
