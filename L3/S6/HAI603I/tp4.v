Inductive is_fact : nat->nat->Prop :=
 | is_fact_0 : is_fact 0 (S 0)
 | is_fact_S : forall n m : nat, is_fact n m -> is_fact (S n) (mult m (S n)).

Fixpoint fact (n : nat) : nat :=
 match n with 
  | 0 => (S 0)
  | (S p) => (mult (fact p) n)
 end.

Require Import FunInd. 
Functional Scheme fact_ind := Induction for fact Sort Prop.
Compute fact 1.

Print fact_ind.

Goal forall n m : nat, (fact n) = m -> (is_fact n m).
Proof.
 induction n.
 intros.
 rewrite <- H.
 simpl.
 apply is_fact_0.
  
 intros.
 rewrite <- H.
 simpl.
 apply is_fact_S.
 apply IHn.
 reflexivity.
Qed.

Goal forall n m : nat, (fact n) = m -> (is_fact n m).
Proof.
 intro.
 functional induction (fact n) using fact_ind.
 intros.
 rewrite <- H.
 auto.
 simpl.
 
Qed.


