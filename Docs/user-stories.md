# User stories – Svømmeklubben Delfinen

| Prioritet | ID | User story | Krav | Størrelse |
|---:|---|---|---|---:|
| 1 | US1 | Opret medlem og få medlemsnummer | F1, F3 | 3 |
| 2 | US2 | Registrér konkurrencesvømmerens discipliner | F2 | 2 |
| 3 | US3 | Beregn årligt kontingent | F5 | 3 |
| 4 | US4 | Se alle medlemmer | F4 | 3 |
| 5 | US5 | Registrér kontingentbetaling | F7 | 2 |
| 6 | US6 | Se medlemmer i restance | F8 | 3 |
| 7 | US7 | Se forventet kontingentindtægt | F6 | 2 |
| 8 | US8 | Se junior- og seniorhold | F9 | 3 |
| 9 | US9 | Registrér bedste træningstid | F10 | 5 |
| 10 | US10 | Registrér stævneresultat | F11 | 3 |
| 11 | US11 | Se en svømmers resultater | F12 | 3 |
| 12 | US12 | Se top 5 pr. disciplin og aldersgruppe | F13 | 5 |
| 13 | US13 | Bevar data mellem programstarter | F14 | 5 |

---

## US1 – Opret medlem og få medlemsnummer

**Som formand vil jeg** oprette et medlem med navn, fødselsdato og medlemsform, **så** klubben kan holde styr på sine medlemmer.

**Acceptkriterier**

- **Givet** et navn, en gyldig fødselsdato og medlemsformen aktiv eller passiv, **når** formanden opretter medlemmet, **så** bliver medlemmet gemt med de indtastede oplysninger.
- **Givet** et aktivt medlem, **når** formanden vælger medlemstype, **så** kan der vælges motionist eller konkurrencesvømmer.
- **Givet** et passivt medlem, **når** formanden vælger medlemstype, **så** skal medlemmet ikke være motionist eller konkurrencesvømmer.
- **Givet** et nyt medlem, **når** medlemmet oprettes, **så** tildeler systemet et medlemsnummer, der er unikt og aldrig genbruges.
- **Givet** en ugyldig dato eller manglende påkrævede oplysninger, **når** formanden forsøger at oprette medlemmet, **så** vises en fejl, og medlemmet oprettes ikke.

## US2 – Registrér konkurrencesvømmerens discipliner

**Som formand vil jeg** vælge de discipliner, en konkurrencesvømmer er aktiv i, **så** træneren kan se svømmerens discipliner.

**Acceptkriterier**

- **Givet** at formanden opretter en konkurrencesvømmer, **når** der vælges én eller flere af butterfly, crawl, rygcrawl og brystsvømning, **så** gemmes de valgte discipliner på svømmeren.
- **Givet** at ingen disciplin er valgt, **når** formanden forsøger at oprette konkurrencesvømmeren, **så** afvises oprettelsen, og systemet forklarer, at mindst én disciplin skal vælges.
- **Givet** at medlemmet er passivt eller motionist, **når** medlemmet oprettes, **så** kræves der ikke discipliner.

## US3 – Beregn årligt kontingent

**Som kasserer vil jeg** se hvert medlems årlige kontingent, **så** jeg kan opkræve det korrekte beløb.

**Acceptkriterier**

- **Givet** et aktivt medlem, der er under 18 år på beregningsdatoen, **når** kontingentet beregnes, **så** er beløbet 1.000 kr.
- **Givet** et aktivt medlem, der er fyldt 18, men endnu ikke 60 år, **når** kontingentet beregnes, **så** er beløbet 1.600 kr.
- **Givet** et aktivt medlem, der fylder 18 år på beregningsdatoen, **når** kontingentet beregnes, **så** er beløbet 1.600 kr.
- **Givet** et aktivt medlem, der er fyldt 60 år, **når** kontingentet beregnes, **så** er beløbet 1.200 kr. (25 % rabat af 1.600 kr.).
- **Givet** et aktivt medlem, der fylder 60 år på beregningsdatoen, **når** kontingentet beregnes, **så** er beløbet 1.200 kr.
- **Givet** et passivt medlem uanset alder, **når** kontingentet beregnes, **så** er beløbet 500 kr.
- **Givet** et medlem med fødselsdato, **når** alderen beregnes, **så** bruges hele år på den valgte beregningsdato; alderen indtastes ikke særskilt.

## US4 – Se alle medlemmer

**Som formand vil jeg** se en liste over alle medlemmer, **så** jeg kan få et samlet overblik over klubben.

**Acceptkriterier**

- **Givet** at klubben har medlemmer, **når** formanden åbner medlemslisten, **så** vises hvert medlems medlemsnummer, navn, alder, junior/senior-status og medlemsform.
- **Givet** et medlem under 18 år på visningsdatoen, **når** listen vises, **så** står medlemmet som junior.
- **Givet** et medlem på 18 år eller derover på visningsdatoen, **når** listen vises, **så** står medlemmet som senior.
- **Givet** at klubben ikke har medlemmer, **når** listen åbnes, **så** vises en tom liste eller en tydelig besked om, at der ikke er medlemmer.

## US5 – Registrér kontingentbetaling

**Som kasserer vil jeg** registrere, at et medlem har betalt årets kontingent, **så** betalingens status er opdateret.

**Acceptkriterier**

- **Givet** et medlem, der ikke har betalt årets kontingent, **når** kassereren registrerer betalingen, **så** står årets kontingent som betalt.
- **Givet** et medlemsnummer, der ikke findes, **når** kassereren forsøger at registrere betaling, **så** vises en fejl, og ingen betaling ændres.
- **Givet** at årets kontingent allerede er registreret som betalt, **når** betalingen registreres igen, **så** får kassereren besked, og betalingen tælles ikke dobbelt.

## US6 – Se medlemmer i restance

**Som kasserer vil jeg** se en liste over medlemmer, der mangler at betale årets kontingent, **så** jeg kan følge op på restancer.

**Acceptkriterier**

- **Givet** medlemmer med ubetalt kontingent, **når** kassereren åbner restancelisten, **så** vises medlemsnummer, navn og skyldige beløb for hvert af dem.
- **Givet** et nyt medlem uden registreret betaling, **når** restancelisten åbnes, **så** vises medlemmet med det fulde kontingentbeløb som skyldig.
- **Givet** et medlem, der har betalt årets kontingent, **når** restancelisten åbnes, **så** vises medlemmet ikke.
- **Givet** at alle medlemmer har betalt, **når** restancelisten åbnes, **så** vises en tydelig besked om, at ingen medlemmer er i restance.

## US7 – Se forventet kontingentindtægt

**Som kasserer vil jeg** se den forventede samlede kontingentindtægt, **så** jeg kan danne mig et overblik over årets kontingenter.

**Acceptkriterier**

- **Givet** medlemmer med forskellige kontingentsatser, **når** kassereren ser den forventede indtægt, **så** vises summen af hvert medlems årlige kontingent.
- **Givet** at nogle medlemmer endnu ikke har betalt, **når** summen beregnes, **så** medregnes deres kontingent stadig.
- **Givet** at klubben ikke har medlemmer, **når** summen vises, **så** er den 0 kr.

## US8 – Se junior- og seniorhold

**Som træner vil jeg** se konkurrencesvømmerne fordelt på junior- og seniorhold med deres aktive discipliner, **så** jeg kan få overblik over holdene.

**Acceptkriterier**

- **Givet** konkurrencesvømmere under 18 år, **når** træneren åbner holdoversigten, **så** vises de på juniorholdet med deres discipliner.
- **Givet** konkurrencesvømmere på 18 år eller derover, **når** træneren åbner holdoversigten, **så** vises de på seniorholdet med deres discipliner.
- **Givet** et medlem, der ikke er konkurrencesvømmer, **når** holdoversigten vises, **så** medtages medlemmet ikke.

## US9 – Registrér bedste træningstid

**Som træner vil jeg** registrere en konkurrencesvømmers træningstid og dato i en disciplin, **så** svømmerens bedste træningsresultat holdes opdateret.

**Acceptkriterier**

- **Givet** en konkurrencesvømmer, en aktiv disciplin, en gyldig tid og en gyldig dato, **når** der ikke findes en tidligere tid i disciplinen, **så** registreres tiden som svømmerens bedste.
- **Givet** en tidligere bedste tid, **når** træneren registrerer en hurtigere tid, **så** erstatter den nye tid og dato den tidligere bedste tid.
- **Givet** en tidligere bedste tid, **når** træneren registrerer en langsommere eller lige så langsom tid, **så** beholdes den tidligere bedste tid, og træneren får besked.
- **Givet** en disciplin, som svømmeren ikke er aktiv i, **når** træneren forsøger at registrere en tid, **så** afvises registreringen.
- **Givet** en ugyldig tid eller dato, **når** træneren forsøger at registrere resultatet, **så** vises en fejl, og resultatet gemmes ikke.

## US10 – Registrér stævneresultat

**Som træner vil jeg** registrere en konkurrencesvømmers stævneresultat, **så** klubbens konkurrenceresultater bliver gemt.

**Acceptkriterier**

- **Givet** en konkurrencesvømmer, et stævnenavn, en gyldig dato, en aktiv disciplin, en placering og en gyldig tid, **når** træneren gemmer resultatet, **så** registreres alle oplysninger på svømmeren.
- **Givet** en disciplin, som svømmeren ikke er aktiv i, **når** træneren forsøger at gemme stævneresultatet, **så** afvises registreringen.
- **Givet** en ugyldig dato, placering eller tid, **når** træneren forsøger at gemme resultatet, **så** vises en fejl, og resultatet gemmes ikke.

## US11 – Se en svømmers resultater

**Som træner vil jeg** se en konkurrencesvømmers bedste træningstider og stævneresultater, **så** jeg kan vurdere svømmerens resultater.

**Acceptkriterier**

- **Givet** et eksisterende medlemsnummer for en konkurrencesvømmer, **når** træneren søger på nummeret, **så** vises svømmerens bedste træningstid med dato for hver disciplin, hvor der findes en tid, samt alle stævneresultater.
- **Givet** et medlemsnummer, der ikke findes, **når** træneren søger, **så** vises en fejlbesked uden at programmet går ned.
- **Givet** et eksisterende medlem, der ikke er konkurrencesvømmer, **når** træneren søger efter resultater, **så** får træneren besked om, at medlemmet ikke har konkurrencesvømmeresultater.

## US12 – Se top 5 pr. disciplin og aldersgruppe

**Som træner vil jeg** se de fem hurtigste juniorer og seniorer i en valgt disciplin, **så** jeg kan sammenligne konkurrencesvømmernes bedste træningstider.

**Acceptkriterier**

- **Givet** en disciplin, **når** træneren åbner top 5, **så** vises junior- og seniorlister hver for sig med navn, bedste træningstid og dato.
- **Givet** flere kvalificerede svømmere i samme aldersgruppe, **når** listen vises, **så** sorteres de efter bedste træningstid med hurtigste tid øverst.
- **Givet** en svømmer, der er aktiv i disciplinen og har en træningstid, **når** listen beregnes, **så** kan svømmeren højst optræde én gang på listen.
- **Givet** færre end fem kvalificerede svømmere i en aldersgruppe, **når** listen vises, **så** vises alle de kvalificerede svømmere.
- **Givet** en konkurrencesvømmer uden træningstid i disciplinen eller uden aktivt medlemskab af disciplinen, **når** listen beregnes, **så** medtages svømmeren ikke.
- **Givet** en ugyldig eller ukendt disciplin, **når** træneren vælger den, **så** vises en fejl, og programmet går ikke ned.

## US13 – Bevar data mellem programstarter

**Som kasserer vil jeg** have, at medlemmer, betalinger og resultater gemmes, når programmet lukkes, **så** klubbens data stadig findes næste gang programmet startes.

**Acceptkriterier**

- **Givet** at medlemmer, betalinger og resultater er registreret, **når** programmet lukkes og startes igen, **så** indlæses de samme data fra tekstfiler.
- **Givet** at en forventet datafil mangler ved opstart, **når** programmet startes, **så** starter klubben tom, og programmet går ikke ned.
- **Givet** at nye oplysninger ændres eller registreres, **når** data gemmes, **så** kan ændringerne læses ind igen ved næste opstart.

---

## Kravdækning

| Krav | User story |
|---|---|
| F1 | US1 |
| F2 | US2 |
| F3 | US1 |
| F4 | US4 |
| F5 | US3 |
| F6 | US7 |
| F7 | US5 |
| F8 | US6 |
| F9 | US8 |
| F10 | US9 |
| F11 | US10 |
| F12 | US11 |
| F13 | US12 |
| F14 | US13 |
