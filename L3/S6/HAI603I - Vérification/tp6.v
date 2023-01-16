Require Import FunInd.

Inductive is_fact : nat->nat->Prop :=
 | is_fact_0 : is_fact 0 (S 0)
 | is_fact_S : forall n m : nat, is_fact n m -> is_fact (S n) (mult m (S n)).

Fixpoint fact (n : nat) : nat :=
 match n with 
  | 0 => (S 0)
  | (S p) => (mult (fact p) n)
 end.

Lemma Lfact : forall n : nat, { v : nat | is_fact n v}.
Proof.
  intros.
  elim n.
  exists (S 0).
  apply is_fact_0.
  intros.
  inversion H.
  exists (mult x (S n0)).
  apply is_fact_S.
  assumption.
Defined.

Require Extraction.
Recursive Extraction Lfact.


