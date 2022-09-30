
**Nivell Bàsic**

- Separar les proves d'exemple (que proven sum) de les proves de la pròpia biblioteca de testing (que proven forAll)
- Llegir els 3 primers blogs de l'agilogy blog:
  - [What is an automated test, again?](https://blog.agilogy.com/2022-05-27-what-is-an-automated-test-again.html)
  - [Testing and persistent state](https://blog.agilogy.com/2022-06-17-testing-and-persistent-state.html)
  - [Testing other side effects](https://blog.agilogy.com/2022-07-08-testing-other-side-effects.html)

**Nivell intermig**

- Escriure proves property based testing de la concatenació de strings. Pensar quines propietats podríem provar i escriure'n les proves com a exemples d'ús de la nostra biblioteca

**Nivell avançat**

- Fer metatests usant el forAll, provar el forall fent property based testing
(per tota propietat dels enters si executo forall d'aquesta propietat, el resultat té aquestes propietats).
    
    - Pista: 
    
    ```scala
    forAll(intPropertyArb){
      val result = Try(forAll(arbInt){intPropertyArb})
      // Assercions sobre result
    }
    ```