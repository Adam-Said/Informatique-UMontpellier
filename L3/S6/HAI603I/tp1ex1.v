Parameter A B C : Prop.

Lemma ex1_1 : A -> B -> A.
Proof.
  intros.
  assumption.
Qed.


Lemma ex1_2 : (A -> B -> C) -> (A -> B) -> A -> C.
Proof.
  intros.
  apply H.
  assumption.
  apply H0.
  assumption.
Qed.

Lemma ex1_3 : A /\ B -> B.
Proof.
  intro.
  elim H.
  intros.
  assumption.
Qed.

Lemma ex1_4 : B -> A \/ B.
Proof.
  intro.
  right.
  assumption.
Qed.

Lemma ex1_5 : (A \/ B) -> (A -> C) -> (B -> C) -> C.
Proof.
  intros.
  elim H.
  intros.
  apply H0.
  assumption.
  assumption.
Qed.

Lemma ex1_6 : A -> False -> ~A.
Proof.
  intros.
  elim H0.
Qed.

Lemma ex1_7 : False -> A.
Proof.
  intro.
  elim H.
Qed.

Lemma ex1_8 : (A <-> B) -> A -> B.
Proof.
  intros.
  elim H.
  intros.
  apply H1.
  assumption.
Qed.

Lemma ex1_9 : (A <-> B) -> B -> A.
Proof.
  intros.
  elim H.
  intros.
  apply H2.
  assumption.
Qed.

Lemma ex1_10: (A -> B) -> (B -> A) -> (A <-> B).
Proof.
  intros.
  split.
  assumption.
  assumption.
Qed.


