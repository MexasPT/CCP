package com.example.data

import com.example.data.model.LegalQuiz
import com.example.data.model.QuizLevel

object CivilQuizLevels {

    val LEVELS: List<QuizLevel> = listOf(
        // LEVEL 1
        QuizLevel(
            levelId = 1,
            title = "Nível 1: Fontes do Direito & Eficácia da Lei",
            subtitle = "Leis, Costume, Analogia, Vacatio e Abuso",
            bookReference = "Parte Geral (Art. 1.º a 13.º e 334.º)",
            description = "Conceitos estruturantes da ordem jurídica portuguesa, interpretação sistemática e limites aos direitos.",
            iconName = "account_balance",
            questions = listOf(
                LegalQuiz(
                    id = 101,
                    question = "Nos termos do Artigo 1.º do Código Civil, quais são as fontes imediatas do direito português?",
                    options = listOf(
                        "As leis e as normas corporativas",
                        "A doutrina e os costumes populares",
                        "Os assentos e as sentenças dos tribunais",
                        "Os regulamentos autárquicos e a equidade"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1.º estabelece que são fontes imediatas do direito as leis e as normas corporativas.",
                    articleId = 1,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 102,
                    question = "Segundo o Artigo 3.º do Código Civil, qual é a relevância do costume na ordem jurídica portuguesa?",
                    options = listOf(
                        "O costume nunca pode ser aplicado pelos tribunais",
                        "Os usos só são atendíveis quando a lei o determine",
                        "O costume prevalece sempre sobre as leis aprovadas",
                        "O costume é fonte imediata de direito equivalente à lei"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 3.º, n.º 1 consagra: 'Os usos que não forem contrários aos princípios da boa fé são atendíveis quando a lei o determine.'",
                    articleId = 3,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 103,
                    question = "De acordo com o Artigo 5.º, n.º 2 do Código Civil, quando começa a vigência da lei se esta não fixar a data?",
                    options = listOf(
                        "No dia seguinte à votação na Assembleia da República",
                        "No prazo fixado na respetiva legislação sobre publicação de atos",
                        "Imediatamente após a promulgação pelo Presidente da República",
                        "30 dias após o anúncio nos meios de comunicação social"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 5.º, n.º 2 remete para a legislação especial (vacatio legis) relativa à publicação e entrada em vigor dos atos normativos.",
                    articleId = 5,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 104,
                    question = "O que estabelece expressamente o Artigo 6.º do Código Civil sobre a ignorância ou má interpretação da lei?",
                    options = listOf(
                        "Justifica o não cumprimento se o cidadão não tiver formação jurídica",
                        "A ignorância ou má interpretação da lei não justifica a falta do seu cumprimento nem isenta das sanções",
                        "Permite a anulação automática de qualquer obrigação civil assumida",
                        "Suspende a eficácia do contrato durante 6 meses"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 6.º fixa o princípio da inescusabilidade: 'A ignorância ou má interpretação da lei não justifica a falta do seu cumprimento nem isenta as pessoas das sanções nela estabelecidas.'",
                    articleId = 6,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 105,
                    question = "Na interpretação da lei (Artigo 9.º do Código Civil), qual é o limite imposto pelo elemento literal?",
                    options = listOf(
                        "Não pode ser considerado pelo intérprete o pensamento legislativo que não tenha na letra da lei um mínimo de correspondência verbal",
                        "O intérprete deve ignorar a letra e focar-se apenas na equidade",
                        "A letra da lei pode ser totalmente contrariada em caso de urgência",
                        "A interpretação deve sempre coincidir com a vontade pessoal do juiz"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 9.º, n.º 2 determina que não pode ser considerado o pensamento legislativo sem um mínimo de correspondência verbal, ainda que imperfeitamente expresso.",
                    articleId = 9,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 106,
                    question = "Como devem ser integrados os casos omissos (lacunas) segundo o Artigo 10.º do Código Civil?",
                    options = listOf(
                        "Pelo recurso prioritário à analogia e, na falta desta, segundo a norma que o próprio intérprete criaria",
                        "Pela anulação de todos os direitos subjetivos das partes",
                        "Pela consulta obrigatória ao Tribunal Constitucional em todos os casos",
                        "Pela recusa de julgamento do tribunal por falta de lei expressa"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 10.º prevê a integração analógica e, na falta de caso análogo, o juiz julga segundo a norma que ele próprio criaria se houvesse de legislar.",
                    articleId = 10,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 107,
                    question = "Qual é o princípio geral consagrado no Artigo 12.º do Código Civil sobre a aplicação das leis no tempo?",
                    options = listOf(
                        "As leis aplicam-se retroactivamente a todos os factos passados",
                        "A lei só dispõe para o futuro; ainda que lhe seja atribuída eficácia retroactiva, presume-se que ficam ressalvados os efeitos já produzidos",
                        "As leis de direito civil prescrevem ao fim de 5 anos",
                        "A nova lei revoga todos os contratos assinados anteriormente sem exceção"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 12.º, n.º 1 consagra expressamente o princípio da não retroactividade da lei civil.",
                    articleId = 12,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 108,
                    question = "Nos termos do Artigo 13.º do Código Civil, que efeito produz a lei interpretativa?",
                    options = listOf(
                        "Integra-se na lei interpretada, ressalvando-se os efeitos já cumpridos e o caso julgado",
                        "Anula a lei anterior desde o momento da sua criação",
                        "Cria obrigações fiscais retroactivas automáticas",
                        "Apenas tem valor orientador sem força obrigatória geral"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 13.º, n.º 1 estabelece que a lei interpretativa se integra na lei interpretada, salvaguardando direitos adquiridos por sentença transitada ou cumprimento.",
                    articleId = 13,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 109,
                    question = "Quando é que o exercício de um direito é ilegítimo por abuso do direito nos termos do Artigo 334.º do Código Civil?",
                    options = listOf(
                        "Quando o titular exceda manifestamente os limites impostos pela boa fé, pelos bons costumes ou pelo fim social ou económico desse direito",
                        "Sempre que o titular consulte um advogado antes de agir",
                        "Apenas quando houver violência física entre as partes",
                        "Quando o direito for exercido fora do horário dos tribunais"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 334.º define abuso do direito quando o titular excede manifestamente os limites da boa fé, bons costumes ou do fim social/económico do direito.",
                    articleId = 334,
                    category = "Fontes do Direito"
                ),
                LegalQuiz(
                    id = 110,
                    question = "A renúncia prévia a direitos legalmente reconhecidos é válida nos termos do Código Civil?",
                    options = listOf(
                        "É sempre válida sem restrições",
                        "Não é permitida a renúncia antecipada a direitos que a lei declare irrenunciáveis",
                        "Depende exclusivamente do consentimento de duas testemunhas",
                        "Só é admitida em contratos de valor inferior a 50 euros"
                    ),
                    correctIndex = 1,
                    explanation = "A ordem jurídica veda a renúncia antecipada a direitos indisponíveis ou quando a lei proíba expressamente a renúncia (ex: Art. 809.º, Art. 2028.º).",
                    articleId = 334,
                    category = "Fontes do Direito"
                )
            )
        ),

        // LEVEL 2
        QuizLevel(
            levelId = 2,
            title = "Nível 2: Pessoas, Personalidade & Capacidade",
            subtitle = "Nascimento, Morte, Maior Acompanhado e Direitos",
            bookReference = "Parte Geral (Art. 66.º a 156.º)",
            description = "Estatuto jurídico da pessoa física, direitos de personalidade e regime tutelar dos adultos vulneráveis.",
            iconName = "person",
            questions = listOf(
                LegalQuiz(
                    id = 201,
                    question = "Segundo o Artigo 66.º, n.º 1 do Código Civil, quando se adquire a personalidade jurídica?",
                    options = listOf(
                        "No momento da conceção biológica",
                        "No momento do nascimento completo e com vida",
                        "Ao completar 18 anos de idade",
                        "Com o registo civil obrigatório aos 30 dias"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 66.º, n.º 1 estipula: 'A personalidade adquire-se no momento do nascimento completo e com vida.'",
                    articleId = 66,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 202,
                    question = "Que direitos a lei reconhece aos nascituros já concebidos nos termos do Artigo 66.º, n.º 2?",
                    options = listOf(
                        "Pleno direito de voto em assembleias de condomínio",
                        "Os direitos que a lei lhes reconhece dependem do seu nascimento efetivo",
                        "Nenhum direito patrimonial ou sucessório",
                        "Apenas direitos de indemnização contra os progenitores"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 66.º, n.º 2 dita que os direitos atribuídos aos nascituros concebidos dependem do seu nascimento com vida.",
                    articleId = 66,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 203,
                    question = "Como cessa a personalidade jurídica singular nos termos do Artigo 68.º, n.º 1 do Código Civil?",
                    options = listOf(
                        "Cessa com a morte",
                        "Cessa com a perda da nacionalidade portuguesa",
                        "Cessa com a declaração de insolvência pessoal",
                        "Cessa quando a pessoa atinge os 100 anos de idade"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 68.º, n.º 1 é taxativo: 'A personalidade cessa com a morte.'",
                    articleId = 68,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 204,
                    question = "Se duas pessoas falecerem no mesmo evento sem se poder apurar qual morreu primeiro (comoriência), qual é a presunção legal (Art. 68.º, n.º 2)?",
                    options = listOf(
                        "Presume-se que a mais idosa faleceu primeiro",
                        "Presume-se que faleceram ao mesmo tempo",
                        "Presume-se que a mulher sobreviveu ao homem",
                        "Presume-se a sobrevivência do herdeiro mais próximo"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 68.º, n.º 2 estabelece a presunção de comoriência: 'Presume-se que faleceram ao mesmo tempo as pessoas que morreram no mesmo evento sem prova em contrário.'",
                    articleId = 68,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 205,
                    question = "De acordo com o Artigo 70.º do Código Civil, qual é a extensão da tutela geral da personalidade?",
                    options = listOf(
                        "A lei protege os indivíduos contra qualquer ofensa ilícita ou ameaça de ofensa à sua personalidade física ou moral",
                        "A tutela limita-se apenas à proteção do património financeiro",
                        "Apenas protege pessoas com nacionalidade portuguesa residente no país",
                        "A tutela de personalidade extingue-se perante o pagamento de caução"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 70.º garante a salvaguarda da personalidade física e moral contra qualquer ofensa ilícita ou ameaça.",
                    articleId = 70,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 206,
                    question = "O direito ao nome e à sua utilização está protegido pelo Código Civil em que termos (Art. 72.º)?",
                    options = listOf(
                        "Qualquer pessoa pode usar o nome de outrem sem autorização",
                        "Toda a pessoa tem direito a usar o seu nome e a opor-se a que outrem o use ilicitamente",
                        "O nome só pode ser alterado por decisão do Governo",
                        "O uso do apelido depende de pagamento de direitos de autor"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 72.º protege o direito ao nome contra usurpações ou uso ilícito por terceiros.",
                    articleId = 72,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 207,
                    question = "A que idade se atinge a maioridade em Portugal e adquire-se plena capacidade de exercício de direitos (Artigo 122.º e 130.º)?",
                    options = listOf(
                        "Aos 16 anos",
                        "Aos 18 anos",
                        "Aos 21 anos",
                        "Aos 25 anos"
                    ),
                    correctIndex = 1,
                    explanation = "A maioridade atinge-se aos 18 anos de idade (Art. 122.º), ficando o indivíduo habilitado a reger a sua pessoa e bens (Art. 130.º).",
                    articleId = 122,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 208,
                    question = "Qual foi o regime que substituiu as antigas figuras da interdição e da inabilitação no Código Civil (Lei n.º 49/2018)?",
                    options = listOf(
                        "Regime do Maior Acompanhado (Art. 138.º e ss.)",
                        "Regime da Tutela Financeira Obrigatória",
                        "Regime da Incapacidade Natural Total",
                        "Regime da Curatela Militar"
                    ),
                    correctIndex = 0,
                    explanation = "A Lei n.º 49/2018 instituiu o regime do Maior Acompanhado (Art. 138.º a 156.º), eliminando as arcaicas interdições.",
                    articleId = 138,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 209,
                    question = "No regime do Maior Acompanhado, qual é o princípio orientador primordial quanto à capacidade do beneficiário (Art. 145.º)?",
                    options = listOf(
                        "O maior acompanhado perde todos os direitos pessoais e civis",
                        "O exercício dos direitos mantém-se livre, salvo as limitações expressamente determinadas pela decisão judicial",
                        "O acompanhante passa a ser proprietário de todos os bens do beneficiário",
                        "É proibido ao maior acompanhado casar ou testar em qualquer circunstância"
                    ),
                    correctIndex = 1,
                    explanation = "O regime rege-se pelo princípio da proporcionalidade e subsidiariedade: o beneficiário conserva a sua liberdade e capacidade, salvo as medidas judiciais estritamente necessárias.",
                    articleId = 145,
                    category = "Pessoas & Personalidade"
                ),
                LegalQuiz(
                    id = 210,
                    question = "As cartas missivas confidenciais podem ser divulgadas por quem as recebeu sem autorização do autor (Art. 75.º)?",
                    options = listOf(
                        "Sim, desde que publicadas em redes sociais privadas",
                        "Não, a carta missiva confidencial não pode ser divulgada sem o consentimento do autor",
                        "Sim, se o destinatário tiver interesse puramente comercial",
                        "Não, a menos que tenham decorrido 2 meses do envio"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 75.º, n.º 1 proíbe a divulgação de cartas missivas confidenciais sem o consentimento do autor.",
                    articleId = 75,
                    category = "Pessoas & Personalidade"
                )
            )
        ),

        // LEVEL 3
        QuizLevel(
            levelId = 3,
            title = "Nível 3: Teoria Geral do Negócio Jurídico",
            subtitle = "Declaração, Vícios da Vontade, Erro, Dolo e Nulidade",
            bookReference = "Parte Geral (Art. 217.º a 295.º)",
            description = "Formação do consentimento, interpretação contratual e patologias negociais invalidantes.",
            iconName = "fact_check",
            questions = listOf(
                LegalQuiz(
                    id = 301,
                    question = "Nos termos do Artigo 217.º do Código Civil, de que formas pode ser feita uma declaração negocial?",
                    options = listOf(
                        "Apenas expressamente através de documento autenticado",
                        "Expressa ou tacitamente (quando se deduz de factos que com toda a probabilidade a revelam)",
                        "Apenas verbalmente na presença de um magistrado",
                        "Exclusivamente por publicação no Diário da República"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 217.º, n.º 1 consagra a declaração expressa ou tácita.",
                    articleId = 217,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 302,
                    question = "O silêncio de uma parte vale como declaração negocial (Artigo 218.º do Código Civil)?",
                    options = listOf(
                        "Sim, quem cala consente em qualquer situação jurídica",
                        "O silêncio só vale como declaração negocial quando esse valor lhe seja atribuído por lei, uso ou convenção",
                        "O silêncio é sempre interpretado como recusa absoluta",
                        "O silêncio só tem valor se for registado perante notário"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 218.º estipula que o silêncio só vale como aceitação quando a lei, o uso ou convenção prévia lhe confiram esse valor.",
                    articleId = 218,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 303,
                    question = "Segundo o Artigo 224.º, n.º 1 do Código Civil, quando se torna eficaz a declaração negocial dirigida a outrem?",
                    options = listOf(
                        "Logo que chega ao poder do destinatário ou é dele conhecida",
                        "No momento exato em que é pensada pelo declarante",
                        "Apenas após o registo no tribunal da comarca",
                        "Decorridos 30 dias após o envio postal"
                    ),
                    correctIndex = 0,
                    explanation = "O Código Civil adota a teoria da receção temperada pela cognoscibilidade (Art. 224.º, n.º 1).",
                    articleId = 224,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 304,
                    question = "Qual é o critério legal geral de interpretação da declaração negocial consagrado no Artigo 236.º, n.º 1 (Teoria da Impressão do Destinatário)?",
                    options = listOf(
                        "O sentido que um declaratário normal, colocado na posição do real declaratário, possa deduzir do comportamento do declarante",
                        "A vontade secreta que o declarante nunca manifestou exteriormente",
                        "O sentido mais favorável à administração tributária",
                        "A interpretação literal estrita sem atender ao contexto"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 236.º, n.º 1 consagra a teoria da impressão do destinatário no direito civil português.",
                    articleId = 236,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 305,
                    question = "Se houver acordo entre declarante e declaratário para enganar terceiros com um negócio aparente (Simulação, Art. 240.º), qual é o vício?",
                    options = listOf(
                        "O negócio simulado é nulo",
                        "O negócio é plenamente válido e inatacável",
                        "O negócio produz efeitos apenas durante 1 ano",
                        "O negócio transforma-se automaticamente em doação pública"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 240.º, n.º 2 declara expressamente: 'O negócio simulado é nulo.'",
                    articleId = 240,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 306,
                    question = "Nos termos do Artigo 247.º, quando é que o erro na declaração (erro-obstáculo) torna o negócio anulável?",
                    options = listOf(
                        "Desde que o declaratário conhecesse ou não devesse ignorar a essencialidade para o declarante do elemento sobre que incidiu o erro",
                        "Basta que o declarante se arrependa do preço acordado",
                        "Apenas se o negócio envolver bens do domínio público",
                        "O erro nunca permite a anulação de contratos civis"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 247.º exige que a essencialidade do elemento sob erro fosse conhecida ou cognoscível pelo declaratário.",
                    articleId = 247,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 307,
                    question = "O que caracteriza o Dolo civil nos termos do Artigo 253.º do Código Civil?",
                    options = listOf(
                        "Qualquer sugestão ou artifício que alguém empregue com a intenção de induzir ou manter em erro o declarante",
                        "A força física que priva totalmente a vontade do declarante",
                        "O mero esquecimento de assinar o contrato na data prevista",
                        "A concessão de descontos comerciais legais"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 253.º define o dolo como qualquer sugestão, artifício ou dissimulação ilícita para induzir ou manter o declarante em erro.",
                    articleId = 253,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 308,
                    question = "Um negócio jurídico cujo objeto seja física ou legalmente impossível, contrário à lei ou à ordem pública é (Artigo 280.º)?",
                    options = listOf(
                        "Nulo",
                        "Válido com redução do preço",
                        "Anulável no prazo de 10 anos",
                        "Ineficaz apenas perante o fisco"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 280.º, n.º 1 determina a nulidade do negócio com objeto física ou legalmente impossível, contrário à lei ou indeterminável.",
                    articleId = 280,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 309,
                    question = "Qual é o regime de invocação da NULIDADE previsto no Artigo 286.º do Código Civil?",
                    options = listOf(
                        "Só pode ser arguida pelo devedor no prazo de 30 dias",
                        "É invocável a todo o tempo por qualquer interessado e pode ser declarada oficiosamente pelo tribunal",
                        "Caduca no prazo de 1 ano se não for registada",
                        "Exige autorização prévia do Ministério Público"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 286.º prevê que a nulidade é invocável a todo o tempo por qualquer interessado e de conhecimento oficioso do tribunal.",
                    articleId = 286,
                    category = "Teoria do Negócio"
                ),
                LegalQuiz(
                    id = 310,
                    question = "Qual é o prazo para arguir a ANULABILIDADE de um negócio jurídico nos termos do Artigo 287.º, n.º 1?",
                    options = listOf(
                        "Um ano subsequente à cessação do vício que lhe serve de fundamento",
                        "Vinte anos a contar da celebração",
                        "A qualquer momento sem limite temporal",
                        "Três meses a contar do envio de carta registada"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 287.º, n.º 1 fixa o prazo de anulação em um ano a contar da cessação do vício ou do seu conhecimento.",
                    articleId = 287,
                    category = "Teoria do Negócio"
                )
            )
        ),

        // LEVEL 4
        QuizLevel(
            levelId = 4,
            title = "Nível 4: Prescrição, Caducidade & Gestão de Prazos",
            subtitle = "Prazos Ordinários, Presuntivas e Interrupção",
            bookReference = "Parte Geral (Art. 296.º a 333.º)",
            description = "Efeitos da passagem do tempo sobre os direitos, prazos gerais e presunções de pagamento.",
            iconName = "schedule",
            questions = listOf(
                LegalQuiz(
                    id = 401,
                    question = "Qual é o prazo ordinário geral da prescrição no Direito Civil português (Artigo 309.º)?",
                    options = listOf(
                        "5 anos",
                        "10 anos",
                        "20 anos",
                        "30 anos"
                    ),
                    correctIndex = 2,
                    explanation = "O Artigo 309.º estipula com clareza: 'O prazo ordinário da prescrição é de vinte anos.'",
                    articleId = 309,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 402,
                    question = "Nos termos do Artigo 310.º do Código Civil, prescrevem no prazo de cinco anos:",
                    options = listOf(
                        "Anuidades de rendas perpétuas ou vitalícias, juros, rendas de locação e pensões de alimentos",
                        "Apenas dívidas fiscais ao Estado",
                        "Ações de reivindicação de direito de propriedade",
                        "Direitos reais de usufruto"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 310.º elenca prestações periodicamente renováveis como juros, rendas, alugueres e pensões de alimentos sujeitas ao prazo de 5 anos.",
                    articleId = 310,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 403,
                    question = "Qual é a natureza jurídica das prescrições presuntivas (Artigo 312.º do Código Civil)?",
                    options = listOf(
                        "Extinguem definitiva e automaticamente o direito do credor sem admissão de prova",
                        "Fundam-se na presunção de cumprimento, podendo ser ilididas pela confissão do devedor",
                        "Aplicam-se exclusivamente a dívidas entre empresas cotadas em bolsa",
                        "Não produzem qualquer efeito prático em tribunal"
                    ),
                    correctIndex = 1,
                    explanation = "As prescrições presuntivas fundam-se na presunção de que a dívida foi paga, podendo ser afastadas por confissão do devedor (Art. 312.º e 313.º).",
                    articleId = 312,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 404,
                    question = "Prescrevem no prazo de 6 meses nos termos do Artigo 316.º do Código Civil:",
                    options = listOf(
                        "Os créditos dos estabelecimentos de alojamento e alimentação pelo fornecimento aos clientes",
                        "As hipotecas voluntárias sobre imóveis",
                        "As obrigações resultantes de contratos promessa",
                        "Os créditos dos bancos por empréstimos hipotecários"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 316.º prevê o prazo presuntivo curto de 6 meses para créditos de alojamento, alimentação e bebidas.",
                    articleId = 316,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 405,
                    question = "Nos termos do Artigo 317.º, prescrevem presuntivamente no prazo de 2 anos:",
                    options = listOf(
                        "Os créditos pelos serviços prestados no exercício de profissões liberais (advogados, médicos, etc.) e comerciantes a não comerciantes",
                        "O direito de herdar bens imóveis",
                        "O direito à anulação de casamentos civis",
                        "Os créditos de seguros de vida após morte"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 317.º fixa a prescrição presuntiva de 2 anos para créditos de comerciantes a consumidores finais e de profissões liberais.",
                    articleId = 317,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 406,
                    question = "Como se interrompe a prescrição de acordo com o Artigo 323.º, n.º 1 do Código Civil?",
                    options = listOf(
                        "Pela citação ou notificação judicial de qualquer ato que exprima a intenção de exercer o direito",
                        "Por mera chamada telefónica gravada sem aviso",
                        "Apenas com a publicação de anúncio em jornal nacional",
                        "Pelo envio de mensagem simples por correio normal"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 323.º, n.º 1 determina a interrupção da prescrição pela citação ou notificação judicial que exprima a intenção de exercer o direito.",
                    articleId = 323,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 407,
                    question = "Qual é o efeito da interrupção da prescrição previsto no Artigo 326.º, n.º 1 do Código Civil?",
                    options = listOf(
                        "Inutiliza para a prescrição todo o tempo decorrido anteriormente, começando a correr novo prazo por inteiro",
                        "Reduz o prazo de prescrição para metade",
                        "Transforma a obrigação civil em obrigação natural perpétua",
                        "Suspende o prazo apenas durante 15 dias"
                    ),
                    correctIndex = 0,
                    explanation = "A interrupção inutiliza todo o tempo já corrido, iniciando-se nova contagem integral a partir do ato interruptivo (Art. 326.º, n.º 1).",
                    articleId = 326,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 408,
                    question = "O que sucede se o devedor cumprir espontaneamente uma obrigação já prescrita (Artigo 304.º, n.º 2)?",
                    options = listOf(
                        "Pode exigir a restituição imediata do que pagou por ser indevido",
                        "Não pode repetir (exigir de volta) o que tiver prestado espontaneamente em cumprimento da obrigação prescrita",
                        "O credor comete um crime de apropriação ilegítima",
                        "O valor reverte automaticamente para o fundo de segurança social"
                    ),
                    correctIndex = 1,
                    explanation = "A obrigação prescrita converte-se em obrigação natural; se paga espontaneamente, não há direito a repetição do prestado (Art. 304.º, n.º 2).",
                    articleId = 304,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 409,
                    question = "O tribunal pode conhecer da prescrição oficiosamente sem que esta seja invocada pelas partes (Artigo 303.º)?",
                    options = listOf(
                        "Sim, o tribunal deve suprir sempre oficiosamente a prescrição",
                        "O tribunal não pode suprir de ofício a prescrição; esta necessita de ser invocada por aquele a quem aproveita",
                        "Apenas se o valor da ação for inferior a 1000 euros",
                        "Apenas em litígios de família e sucessões"
                    ),
                    correctIndex = 1,
                    explanation = "O Artigo 303.º estatui expressamente: 'O tribunal não pode suprir, de ofício, a prescrição; esta necessita, para ser eficaz, de ser invocada, judicial ou extrajudicialmente, por aquele a quem aproveita.'",
                    articleId = 303,
                    category = "Prescrição & Prazos"
                ),
                LegalQuiz(
                    id = 410,
                    question = "A caducidade pode ser impedida pela prática do ato a que a lei ou convenção subordina a conservação do direito (Artigo 331.º)?",
                    options = listOf(
                        "Sim, a caducidade é impedida pela prática do ato dentro do prazo legal",
                        "Não, a caducidade nunca pode ser impedida sob qualquer forma",
                        "Apenas se houver aceitação escrita do tribunal no mesmo dia",
                        "Apenas se o prazo for superior a 10 anos"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 331.º, n.º 1 determina que só a prática do ato no prazo fixado impede a caducidade.",
                    articleId = 331,
                    category = "Prescrição & Prazos"
                )
            )
        ),

        // LEVEL 5
        QuizLevel(
            levelId = 5,
            title = "Nível 5: Direito das Obrigações & Cumprimento",
            subtitle = "Pontualidade, Mora, Resolução e Sinal",
            bookReference = "Livro II (Art. 397.º a 482.º e 762.º a 812.º)",
            description = "Princípios do cumprimento obrigacional, contrato-promessa, sinal e resolução contratual.",
            iconName = "handshake",
            questions = listOf(
                LegalQuiz(
                    id = 501,
                    question = "Qual é o princípio orientador das relações contratuais consagrado no Artigo 406.º, n.º 1 (Pacta sunt servanda)?",
                    options = listOf(
                        "O contrato deve ser pontualmente cumprido, e só pode modificar-se ou extinguir-se por mútuo consentimento dos contraentes ou nos casos admitidos na lei",
                        "Qualquer contraente pode desistir do contrato unilateralmente sem indemnização",
                        "O contrato só obriga após confirmação por duas testemunhas",
                        "As partes apenas são obrigadas se o contrato tiver sido vantajoso economicamente"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 406.º, n.º 1 consagra o princípio fundamental da estabilidade e pontualidade contratual.",
                    articleId = 406,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 502,
                    question = "No contrato-promessa de compra e venda com constituição de sinal (Artigo 442.º, n.º 2), o que sucede se quem constituiu o sinal não cumprir a sua obrigação?",
                    options = listOf(
                        "Perde o sinal prestado em benefício da outra parte",
                        "Exige o dobro do valor que entregou",
                        "Fica livre de qualquer penalização",
                        "O contrato converte-se automaticamente em doação pura"
                    ),
                    correctIndex = 0,
                    explanation = "Se quem prestou o sinal faltar ao cumprimento, a outra parte tem o direito de fazer sua a coisa entregue (Art. 442.º, n.º 2).",
                    articleId = 442,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 503,
                    question = "Se quem recebeu o sinal não cumprir culposamente o contrato-promessa (Artigo 442.º, n.º 2), o que pode exigir a contraparte não faltosa?",
                    options = listOf(
                        "A restituição do sinal em dobro",
                        "Apenas o valor exato entregue sem quaisquer juros",
                        "A perda de todos os seus direitos indemnizatórios",
                        "A anulação de todo o património do devedor"
                    ),
                    correctIndex = 0,
                    explanation = "Se o não cumprimento for de quem recebeu o sinal, a outra parte tem a faculdade de exigir o dobro do que prestou (Art. 442.º, n.º 2).",
                    articleId = 442,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 504,
                    question = "Quando é admissível a resolução ou modificação do contrato por alteração anormal das circunstâncias (Artigo 437.º)?",
                    options = listOf(
                        "Se as circunstâncias em que as partes fundaram a decisão de contratar tiverem sofrido uma alteração anormal e grave que torne a exigência da obrigação contrária à boa fé",
                        "Sempre que uma das partes encontre um fornecedor mais barato",
                        "Apenas em contratos celebrados entre familiares",
                        "Apenas quando o valor do contrato ultrapasse um milhão de euros"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 437.º consagra a cláusula rebus sic stantibus para tutela do equilíbrio contratual perante alterações imprevistas e anormais.",
                    articleId = 437,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 505,
                    question = "Nos termos do Artigo 473.º do Código Civil, quem, sem causa justificativa, enriquecer à custa de outrem:",
                    options = listOf(
                        "É obrigado a restituir aquilo com que injustamente se locupletou",
                        "Pode conservar 50% do valor enriquecido",
                        "Não tem qualquer dever de restituição perante a lei civil",
                        "Fica apenas sujeito a advertência verbal"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 473.º, n.º 1 consagra o dever geral de restituição do enriquecimento sem causa.",
                    articleId = 473,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 506,
                    question = "O Artigo 762.º, n.º 2 do Código Civil obriga as partes a que comportamento no cumprimento e exercício do direito?",
                    options = listOf(
                        "Proceder de boa fé tanto no cumprimento da obrigação como no exercício do direito correspondente",
                        "Exigir o pagamento exclusivamente em moeda de ouro",
                        "Recusar qualquer contacto antes de decorrido o prazo",
                        "Privilegiar sempre os interesses de terceiros estranhos ao negócio"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 762.º, n.º 2 impõe os ditames da boa fé tanto no cumprimento como no exercício dos direitos creditórios.",
                    articleId = 762,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 507,
                    question = "Quando se considera o devedor constituído em mora (Artigo 805.º do Código Civil)?",
                    options = listOf(
                        "Após ter sido judicial ou extrajudicialmente interpelado para cumprir, salvo se a obrigação tiver prazo certo",
                        "Logo no primeiro dia do ano civil",
                        "Apenas com sentença final de condenação transitada em julgado",
                        "Apenas após 5 anos de atraso contínuo"
                    ),
                    correctIndex = 0,
                    explanation = "Nos termos do Artigo 805.º, n.º 1 e 2, há mora após interpelação ou independentemente dela se houver prazo certo.",
                    articleId = 805,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 508,
                    question = "O que permite a convenção de uma Cláusula Penal entre as partes (Artigo 810.º do Código Civil)?",
                    options = listOf(
                        "Fixar previamente por acordo o montante da indemnização exigível em caso de incumprimento da obrigação",
                        "Aplicar penas de prisão ao contraente em dívida",
                        "Impedir que o devedor consulte advogados",
                        "Excluir totalmente o pagamento de juros legais"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 810.º permite estipular por cláusula penal a liquidação prévia do dano decorrente do não cumprimento ou mora.",
                    articleId = 810,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 509,
                    question = "Se a prestação debitória se tornar impossível por causa não imputável ao devedor (Artigo 790.º, n.º 1):",
                    options = listOf(
                        "A obrigação extingue-se",
                        "O devedor deve pagar o triplo do valor acordado",
                        "O devedor responde com o seu património presente e futuro",
                        "A obrigação transfere-se compulsoriamente para o Estado"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 790.º, n.º 1 estipula: 'A obrigação extingue-se quando a prestação se torna impossível por causa não imputável ao devedor.'",
                    articleId = 790,
                    category = "Obrigações"
                ),
                LegalQuiz(
                    id = 510,
                    question = "A compensação de créditos é admissível para extinguir duas dívidas recíprocas (Artigo 847.º) quando:",
                    options = listOf(
                        "Os créditos sejam judicialmente exigíveis e de coisas fungíveis da mesma espécie e qualidade",
                        "As duas dívidas tenham sido contraídas em países diferentes",
                        "Uma das partes recuse terminantemente qualquer acerto de contas",
                        "A dívida principal já tenha sido perdoada"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 847.º fixa os requisitos da compensação: reciprocidade, exigibilidade judicial e homogeneidade das coisas devidas.",
                    articleId = 847,
                    category = "Obrigações"
                )
            )
        ),

        // LEVEL 6
        QuizLevel(
            levelId = 6,
            title = "Nível 6: Responsabilidade Civil & Indemnização",
            subtitle = "Facto Ilícito, Culpa, Risco, Danos e Reparação",
            bookReference = "Livro II (Art. 483.º a 510.º e 562.º a 572.º)",
            description = "Pressupostos da responsabilidade aquiliana, responsabilidade pelo risco e cálculo das indemnizações.",
            iconName = "balance",
            questions = listOf(
                LegalQuiz(
                    id = 601,
                    question = "Quais são os 5 pressupostos cumulativos da responsabilidade civil delitual segundo o Artigo 483.º, n.º 1 do Código Civil?",
                    options = listOf(
                        "Facto voluntário, ilicitude, culpa, dano e nexo de causalidade entre o facto e o dano",
                        "Apenas a existência de seguro e contrato escrito prévio",
                        "Comportamento involuntário, enriquecimento sem causa e ausência de dano",
                        "Dano corporal grave e queixa-crime no prazo de 48 horas"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 483.º consagra a obrigação de indemnizar com base na verificação simultânea de facto, ilicitude, imputação culposa, dano e nexo de causalidade.",
                    articleId = 483,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 602,
                    question = "Qual é o critério legal para apreciação da CULPA na responsabilidade civil (Artigo 487.º, n.º 2)?",
                    options = listOf(
                        "A culpa é apreciada pela diligência de um bom pai de família, em face das circunstâncias de cada caso",
                        "A culpa é sempre presumida de forma inilidível contra quem tiver mais rendimentos",
                        "A culpa só existe se comprovada a intenção deliberada de matar",
                        "A culpa é fixada por sorteio judicial em caso de dúvida"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 487.º, n.º 2 adota o padrão abstrato do homem médio prudente: 'a diligência de um bom pai de família'.",
                    articleId = 487,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 603,
                    question = "Nos termos do Artigo 500.º do Código Civil, o comitente responde pelos danos causados pelo comissário?",
                    options = listOf(
                        "Responde independentemente de culpa pelos danos que o comissário causar, desde que sobre este recaia também a obrigação de indemnizar no exercício da função",
                        "Nunca responde, sendo a responsabilidade exclusiva do empregado",
                        "Apenas responde se o ato tiver sido praticado aos fins de semana",
                        "Apenas responde perante indemnizações inferiores a 500 euros"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 500.º consagra a responsabilidade objetiva do comitente pelos atos praticados pelo comissário no exercício das funções confiadas.",
                    articleId = 500,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 604,
                    question = "Na responsabilidade pelo risco na condução de veículos terrestres a motor (Artigo 503.º, n.º 1), quem responde pelos danos decorrentes dos riscos do veículo?",
                    options = listOf(
                        "Aquele que tiver a direção efetiva do veículo e o utilizar no seu próprio interesse",
                        "Apenas a fábrica que montou os pneus da viatura",
                        "O passageiro sentado no banco traseiro",
                        "A entidade gestora da autoestrada em todos os casos"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 503.º, n.º 1 responsabiliza quem tem a direção efetiva do veículo automóvel pelos danos próprios dos seus riscos.",
                    articleId = 503,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 605,
                    question = "De acordo com o Artigo 496.º, n.º 1 do Código Civil, que danos não patrimoniais (morais) devem ser indemnizados?",
                    options = listOf(
                        "Aqueles que, pela sua gravidade, mereçam a tutela do direito",
                        "Todos os pequenos aborrecimentos diários da vida em sociedade",
                        "Apenas despesas com compras de medicamentos sem receita",
                        "Nenhuns danos morais são indemnizáveis no direito civil português"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 496.º, n.º 1 estabelece que na fixação da indemnização deve atender-se aos danos não patrimoniais que mereçam tutela pelo seu grau de gravidade.",
                    articleId = 496,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 606,
                    question = "Qual é o princípio orientador da obrigação de indemnização estipulado no Artigo 562.º do Código Civil?",
                    options = listOf(
                        "Quem estiver obrigado a reparar um dano deve reconstituir a situação que existiria se não se tivesse verificado o evento causador da obrigação (Reconstituição Natural)",
                        "Pagar uma taxa fixa de 10% sobre o património global do causador",
                        "Transferir a propriedade da casa do lesante para a vítima",
                        "Aplicar multas fiscais sem reparar o prejuízo efetivo da vítima"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 562.º consagra o primado da reconstituição natural do estado anterior à lesão.",
                    articleId = 562,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 607,
                    question = "Quando é que a indemnização é fixada em dinheiro (Artigo 566.º, n.º 1 do Código Civil)?",
                    options = listOf(
                        "Sempre que a reconstituição natural não seja possível, não repare inteiramente os danos ou seja excessivamente onerosa para o devedor",
                        "Apenas se o lesado for uma sociedade comercial",
                        "Apenas quando o tribunal não tiver tempo para avaliar o bem danificado",
                        "É sempre proibida a fixação em dinheiro na lei portuguesa"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 566.º, n.º 1 estabelece o caráter subsidiário da indemnização por equivalente pecuniário.",
                    articleId = 566,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 608,
                    question = "Que doutrina sobre o nexo de causalidade consagra o Artigo 563.º do Código Civil?",
                    options = listOf(
                        "A teoria da causalidade adequada (só responde pelos danos que o facto era apto a provocar em abstrato)",
                        "A teoria da equivalência pura das condições sem qualquer filtro",
                        "A teoria do acaso fortuito absoluto",
                        "A presunção de culpa de todos os presentes no local"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 563.º acolhe na ordem jurídica portuguesa a teoria da causalidade adequada formulada por von Kries.",
                    articleId = 563,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 609,
                    question = "Se um facto culposo do próprio lesado tiver concorrido para a produção ou agravamento dos danos (Artigo 570.º, n.º 1):",
                    options = listOf(
                        "O tribunal pode reduzir a indemnização ou mesmo excluí-la de acordo com a gravidade das culpas",
                        "O lesado recebe o dobro do valor como compensação",
                        "O réu perde automaticamente o direito de apresentar testemunhas",
                        "A ação é arquivada sem qualquer análise de prova"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 570.º, n.º 1 prevê a redução ou exclusão da obrigação de indemnizar em caso de culpa concorrente da vítima.",
                    articleId = 570,
                    category = "Responsabilidade Civil"
                ),
                LegalQuiz(
                    id = 610,
                    question = "Qual é o prazo de prescrição do direito de indemnização fundado em responsabilidade extracontratual (Artigo 498.º, n.º 1)?",
                    options = listOf(
                        "3 anos a contar da data em que o lesado teve conhecimento do direito que lhe compete",
                        "20 anos sem necessidade de prova",
                        "6 meses a contar do facto gerador do dano",
                        "1 mês a contar do envio de carta registada"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 498.º, n.º 1 estabelece o prazo especial de 3 anos para a responsabilidade civil delitual.",
                    articleId = 498,
                    category = "Responsabilidade Civil"
                )
            )
        ),

        // LEVEL 7
        QuizLevel(
            levelId = 7,
            title = "Nível 7: Garantias das Obrigações & Não Cumprimento",
            subtitle = "Fiança, Hipoteca, Penhor, Retenção e Impugnação",
            bookReference = "Livro II (Art. 601.º a 761.º)",
            description = "Garantias pessoais e reais, privilégios creditórios e meios de conservação da garantia patrimonial.",
            iconName = "shield",
            questions = listOf(
                LegalQuiz(
                    id = 701,
                    question = "O que constitui a garantia geral das obrigações nos termos do Artigo 601.º do Código Civil?",
                    options = listOf(
                        "Todos os bens do devedor suscetíveis de penhora respondem pelo cumprimento das suas obrigações",
                        "Apenas o dinheiro existente na conta bancária no momento do contrato",
                        "O património exclusivo dos pais do devedor",
                        "Apenas bens imóveis registados há mais de 10 anos"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 601.º consagra o princípio fundamental de que o património penhorável do devedor responde genericamente pelas suas dívidas.",
                    articleId = 601,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 702,
                    question = "O que caracteriza a Fiança nos termos do Artigo 627.º do Código Civil?",
                    options = listOf(
                        "O fiador garante a satisfação do direito de crédito, ficando pessoalmente obrigado perante o credor",
                        "O fiador entrega imediatamente todos os seus imóveis ao credor",
                        "O fiador apenas responde moralmente sem qualquer impacto financeiro",
                        "A fiança é proibida em contratos bancários"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 627.º, n.º 1 define a fiança como garantia pessoal prestada por terceiro em benefício do credor.",
                    articleId = 627,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 703,
                    question = "O que é o Benefício da Excussão Prévia conferido ao fiador simples (Artigo 638.º, n.º 1 do Código Civil)?",
                    options = listOf(
                        "O fiador pode recusar o pagamento enquanto o credor não tiver executado todos os bens do devedor principal sem obter a satisfação do crédito",
                        "O fiador pode exigir o perdão de 50% de todas as suas dívidas pessoais",
                        "O fiador tem prioridade absoluta na compra dos bens penhorados",
                        "O fiador não pode ser notificado pelo tribunal"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 638.º consagra o benefício de excussão prévia, resguardando o património do fiador até ao esgotamento dos bens do devedor afiançado.",
                    articleId = 638,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 704,
                    question = "O que confere a Hipoteca ao credor nos termos do Artigo 686.º, n.º 1 do Código Civil?",
                    options = listOf(
                        "O direito de ser pago pelo valor de certas coisas imóveis, ou equiparadas, com preferência sobre os demais credores que não gozem de privilégio especial",
                        "A posse física e o direito de habitar o imóvel de imediato sem escritura",
                        "O poder de vender o imóvel do devedor em hasta pública sem autorização do tribunal",
                        "A propriedade imediata do imóvel logo no dia da celebração do empréstimo"
                    ),
                    correctIndex = 0,
                    explanation = "A hipoteca (Art. 686.º) confere preferência de pagamento sobre o valor do bem onerado face aos credores comuns.",
                    articleId = 686,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 705,
                    question = "A hipoteca voluntária sobre um imóvel é válida sem registo predial (Artigo 687.º do Código Civil)?",
                    options = listOf(
                        "A hipoteca deve ser registada, sob pena de não produzir efeitos, mesmo entre as partes",
                        "Produz plenos efeitos mesmo sem registo",
                        "O registo é apenas facultativo para quem pretenda pagar menos impostos",
                        "O registo pode ser substituído por documento assinado com duas testemunhas"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 687.º é perentório: o registo da hipoteca é constitutivo da garantia real.",
                    articleId = 687,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 706,
                    question = "Em que consiste o Penhor de coisas móveis regulado no Artigo 666.º do Código Civil?",
                    options = listOf(
                        "A entrega de uma coisa móvel ou de um direito de crédito para garantia do cumprimento da obrigação",
                        "A destruição voluntária de bens do devedor",
                        "A compra forçada de ações em bolsa",
                        "A transferência da nacionalidade do titular dos bens"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 666.º define o penhor de coisas ou direitos conferindo ao credor a preferência no pagamento sobre o valor do bem empenhado.",
                    articleId = 666,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 707,
                    question = "Quem tem Direito de Retenção nos termos do Artigo 754.º do Código Civil?",
                    options = listOf(
                        "O devedor que disponha de um crédito resultante de despesas feitas por causa da coisa ou de danos por ela causados pode reter a coisa enquanto não for pago",
                        "Qualquer inquilino pode reter o imóvel sem pagar rendas por tempo indefinido",
                        "O comprador que se recuse a assinar a escritura pública",
                        "Apenas as instituições públicas de crédito"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 754.º confere direito de retenção ao possuidor/detentor da coisa por créditos decorrentes de benfeitorias ou danos causados pela própria coisa.",
                    articleId = 754,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 708,
                    question = "A cláusula de 'Pacto Comissório' (pela qual o credor faz sua a coisa hipotecada ou empenhada se a dívida não for paga) é válida no direito civil (Artigo 694.º)?",
                    options = listOf(
                        "É expressamente nula",
                        "É sempre válida e recomendada em contratos bancários",
                        "É válida apenas para dívidas superiores a 500 mil euros",
                        "É válida se celebrada perante solicitador"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 694.º proíbe com nulidade absoluta o pacto comissório para proteger o devedor contra a apropriação abusiva dos seus bens pelo credor.",
                    articleId = 694,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 709,
                    question = "Qual é o objetivo da Ação de Impugnação Pauliana prevista no Artigo 610.º do Código Civil?",
                    options = listOf(
                        "Permitir aos credores atacar e privar de eficácia atos do devedor que diminuam a sua garantia patrimonial em prejuízo dos créditos",
                        "Exigir a falência compulsória de todos os sócios de uma empresa",
                        "Anular o casamento do devedor celebrado no estrangeiro",
                        "Alterar os prazos de prescrição fixados na lei"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 610.º consagra a impugnação pauliana como meio de conservação da garantia patrimonial contra atos fraudulentos do devedor.",
                    articleId = 610,
                    category = "Garantias"
                ),
                LegalQuiz(
                    id = 710,
                    question = "A Cessão de Créditos (Artigo 577.º do Código Civil) exige a autorização ou consentimento do devedor para ser válida?",
                    options = listOf(
                        "O credor pode ceder a terceiro o seu crédito independentemente do consentimento do devedor, desde que a lei ou convenção não o proíba",
                        "Exige obrigatoriamente a assinatura do devedor em cartório notarial",
                        "Só é permitida se o devedor for uma entidade pública",
                        "É proibida em todos os contratos civis de financiamento"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 577.º, n.º 1 dita que a cessão de créditos prescinde do consentimento do devedor cedido, bastando a sua notificação (Art. 583.º).",
                    articleId = 577,
                    category = "Garantias"
                )
            )
        ),

        // LEVEL 8
        QuizLevel(
            levelId = 8,
            title = "Nível 8: Contratos em Especial",
            subtitle = "Compra e Venda, Doação, Arrendamento e Mútuo",
            bookReference = "Livro II (Art. 874.º a 1151.º)",
            description = "Regimes típicos de contratos solenes, garantias contra vícios de bens e arrendamento urbano.",
            iconName = "menu_book",
            questions = listOf(
                LegalQuiz(
                    id = 801,
                    question = "Quais são os 3 efeitos essenciais da Compra e Venda nos termos do Artigo 879.º do Código Civil?",
                    options = listOf(
                        "A transmissão da propriedade da coisa, a obrigação de entregar a coisa e a obrigação de pagar o preço",
                        "A emissão de recibo, a vistoria predial e a caução bancária",
                        "O pagamento em prestações, a fiança obrigatória e a publicação em jornal",
                        "A celebração de contrato de trabalho temporário concomitante"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 879.º enumera os três efeitos estruturantes: transmissão real do direito de propriedade, obrigação de entrega e obrigação de pagar o preço.",
                    articleId = 879,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 802,
                    question = "Que forma solene é exigida para a validade da Compra e Venda de Bens Imóveis segundo o Artigo 875.º do Código Civil?",
                    options = listOf(
                        "Escritura pública ou documento particular autenticado (DPA)",
                        "Mero aperto de mão com recibo manuscrito simples",
                        "Troca de mensagens de correio eletrónico com comprovativo bancário",
                        "Declaração verbal na junta de freguesia local"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 875.º do Código Civil exige escritura pública ou documento particular autenticado para alienação de imóveis.",
                    articleId = 875,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 803,
                    question = "Qual é o valor jurídico da Venda de Bens Alheios no Código Civil português (Artigo 892.º)?",
                    options = listOf(
                        "A venda de bens alheios é nula",
                        "A venda é plenamente válida desde que o comprador pague em dinheiro vivo",
                        "A venda transfere a posse imediata ao comprador sem anulação",
                        "A venda é convertida compulsoriamente em arrendamento perpétuo"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 892.º comina expressamente com a nulidade a venda de coisa alheia quando o vendedor careça de legitimidade para a alienar.",
                    articleId = 892,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 804,
                    question = "Se a coisa vendida padecer de vícios que a desvalorizem ou impeçam a realização do fim a que se destina (Artigo 913.º - Venda de Coisas Defeituosas):",
                    options = listOf(
                        "O comprador tem direito à reparação da coisa, à sua substituição, à redução do preço ou à resolução do contrato",
                        "O comprador não tem qualquer remédio jurídico se já tiver pago",
                        "O vendedor pode reter 80% do preço a título de despesas operacionais",
                        "A garantia limita-se apenas a bens móveis usados com mais de 20 anos"
                    ),
                    correctIndex = 0,
                    explanation = "Os Artigos 913.º e ss. conferem ao comprador de coisas defeituosas os direitos sucessivos de eliminação do defeito, substituição, redução do preço ou resolução.",
                    articleId = 913,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 805,
                    question = "A Doação exige a aceitação do donatário para ser perfeita e produzir efeitos (Artigo 945.º do Código Civil)?",
                    options = listOf(
                        "Sim, a doação é um contrato e torna-se perfeita logo que o donatário a aceite",
                        "Não, a doação é um ato unilateral que dispensa qualquer aceitação",
                        "Apenas se o donatário for menor de idade",
                        "Apenas se envolver bens móveis com valor inferior a 10 euros"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 945.º, n.º 1 determina a necessidade de aceitação do donatário para a perfeição da doação contratual.",
                    articleId = 945,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 806,
                    question = "A doação pode ser revogada pelo doador com fundamento em Ingratidão do donatário (Artigo 970.º)?",
                    options = listOf(
                        "Sim, se o donatário se tornar indigno por praticar ato ilícito grave contra a pessoa ou honra do doador ou seus familiares",
                        "Não, as doações são irrevogáveis mesmo em caso de crime doloso",
                        "Apenas se o donatário mudar de domicílio fiscal",
                        "Apenas no prazo de 30 dias após o Natal"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 970.º e 974.º consagram o direito do doador à revogação por ingratidão do donatário.",
                    articleId = 970,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 807,
                    question = "No contrato de Arrendamento Urbano, que dever recai sobre o senhorio nos termos do Artigo 1031.º, alínea b)?",
                    options = listOf(
                        "Assegurar ao arrendatário o gozo da coisa locada para os fins a que a mesma se destina",
                        "Pagar as despesas de alimentação diária do inquilino",
                        "Visitar a casa todos os dias sem aviso prévio",
                        "Assumir as multas de trânsito dos veículos do inquilino"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1031.º, alínea b) impõe ao locador a obrigação fulcral de assegurar ao locatário o gozo pacífico da coisa locada.",
                    articleId = 1031,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 808,
                    question = "O arrendatário habitacional com mais de 2 anos de contrato tem direito de preferência na compra do imóvel arrendado (Artigo 1091.º, n.º 1)?",
                    options = listOf(
                        "Sim, tem direito de preferência na compra e venda do imóvel ou fração arrendada",
                        "Não, os inquilinos nunca têm qualquer preferência de compra",
                        "Apenas se tiver pago 10 anos de rendas adiantadas",
                        "Apenas se for parente consanguíneo do senhorio"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1091.º, n.º 1, alínea a) consagra o direito legal de preferência do arrendatário habitacional.",
                    articleId = 1091,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 809,
                    question = "Nos termos do Artigo 1142.º do Código Civil, o que é o Contrato de Mútuo?",
                    options = listOf(
                        "O contrato pelo qual uma das partes empresta à outra dinheiro ou outra coisa fungível, ficando a segunda obrigada a restituir outro tanto do mesmo género e qualidade",
                        "A cedência temporária e gratuita de uma casa para férias",
                        "A prestação de trabalho subordinado sob direção de outrem",
                        "O depósito de mercadorias num armazém portuário"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1142.º define o mútuo civil como o empréstimo de dinheiro ou coisas fungíveis com obrigação de restituição do equivalente.",
                    articleId = 1142,
                    category = "Contratos"
                ),
                LegalQuiz(
                    id = 810,
                    question = "O que estabelece o Artigo 1146.º do Código Civil relativamente a juros usurários no mútuo?",
                    options = listOf(
                        "É havido como usurário o contrato de mútuo em que sejam estipulados juros anuais que excedam a taxa legal em mais do limite fixado por lei, sendo o excesso reduzido ao limite legal",
                        "Permite juros livres e infinitos sem qualquer teto legal",
                        "O mútuo nunca pode render quaisquer juros em Portugal",
                        "Apenas as empresas privadas podem cobrar taxas usurárias"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1146.º comina a usura nos juros com a nulidade parcial e redução compulsória aos limites máximos legais.",
                    articleId = 1146,
                    category = "Contratos"
                )
            )
        ),

        // LEVEL 9
        QuizLevel(
            levelId = 9,
            title = "Nível 9: Direitos Reais, Posse & Condomínio",
            subtitle = "Posse, Usucapião, Acessão e Frações Autónomas",
            bookReference = "Livro III (Art. 1251.º a 1575.º)",
            description = "Doutrina da posse, aquisição originária por usucapião, propriedade horizontal e encargos condominiais.",
            iconName = "domain",
            questions = listOf(
                LegalQuiz(
                    id = 901,
                    question = "Qual é a definição legal de POSSE adotada no Artigo 1251.º do Código Civil Português?",
                    options = listOf(
                        "O poder que se manifesta quando alguém atua por forma correspondente ao exercício do direito de propriedade ou de outro direito real",
                        "A mera inscrição no registo automóvel sem contacto com o bem",
                        "O consentimento verbal do presidente da câmara municipal",
                        "A propriedade exclusiva garantida por seguro de vida"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1251.º define a posse pela atuação fáctica exteriorizada correspondente ao exercício de um direito real.",
                    articleId = 1251,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 902,
                    question = "Segundo o Artigo 1253.º do Código Civil, quem são havidos como meros detentores ou possuidores precários?",
                    options = listOf(
                        "Os que exercem o poder de facto sem intenção de agir como beneficiários do direito ou por mera tolerância do titular",
                        "Apenas os proprietários plenos com escritura notarial",
                        "Os juízes que decidem ações de despejo",
                        "Os bancos que concedem crédito à habitação"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1253.º elenca os detentores precários que detêm a coisa em nome de outrem ou por mera tolerância fáctica.",
                    articleId = 1253,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 903,
                    question = "A presunção de titularidade do direito a favor do possuidor está prevista em que termos no Artigo 1268.º, n.º 1?",
                    options = listOf(
                        "O possuidor goza da presunção da titularidade do direito, exceto se houver a favor de outrem presunção fundada em registo anterior",
                        "A posse nunca gera qualquer presunção jurídica",
                        "O possuidor tem de provar a sua inocência perante o fisco anualmente",
                        "Apenas a posse de automóveis beneficia de presunção"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1268.º, n.º 1 consagra a presunção legal de titularidade do direito a favor do possuidor fáctico.",
                    articleId = 1268,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 904,
                    question = "Nos termos do Artigo 1287.º do Código Civil, o que é a USUCAPIÃO?",
                    options = listOf(
                        "A aquisição do direito de propriedade ou de outros direitos reais de gozo facultada pela posse mantida por certo lapso de tempo",
                        "O confisco de terrenos agrícolas abandonados pelo Estado",
                        "A partilha forçada de heranças sem testamento",
                        "O pagamento em atraso de taxas moderadoras de saúde"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1287.º define a usucapião como modalidade originária de aquisição de direitos reais com base na posse reiterada no tempo.",
                    articleId = 1287,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 905,
                    question = "Na usucapião de imóveis, havendo título de aquisição e registo do título de boa fé (Artigo 1294.º, al. a), qual é o prazo?",
                    options = listOf(
                        "10 anos a contar da data do registo",
                        "1 ano",
                        "30 anos",
                        "50 anos"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1294.º, alínea a) fixa o prazo de 10 anos quando exista título registado e boa fé do possuidor.",
                    articleId = 1294,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 906,
                    question = "Se a posse sobre o imóvel não for titulada e for de má fé (Artigo 1296.º), em quanto tempo se dá a usucapião?",
                    options = listOf(
                        "20 anos",
                        "5 anos",
                        "100 anos",
                        "A usucapião é eternamente impossível"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1296.º fixa em 20 anos o prazo máximo de usucapião para posse não titulada e de má fé.",
                    articleId = 1296,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 907,
                    question = "Na Propriedade Horizontal (Artigo 1414.º), que tipo de direitos coexistem sobre as frações e o edifício?",
                    options = listOf(
                        "Cada condómino é proprietário exclusivo da sua fração e comproprietário das partes comuns do edifício",
                        "Todos os condóminos dividem igualmente os quartos de todos os apartamentos",
                        "O edifício pertence 100% à empresa de administração de condomínios",
                        "As escadas e telhados pertencem exclusivamente ao condómino do rés-do-chão"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1420.º consagra a titularidade incindível: direito de propriedade exclusiva sobre a fração autónoma e compropriedade nas partes comuns.",
                    articleId = 1420,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 908,
                    question = "São obrigatoriamente Partes Comuns no edifício em propriedade horizontal nos termos do Artigo 1421.º, n.º 1:",
                    options = listOf(
                        "O solo, os alicerces, colunas, pilares, paredes mestras, o telhado ou terraços de cobertura, as escadas e entradas",
                        "Apenas os móveis que estejam no interior de cada sala privada",
                        "Os eletrodomésticos de cada habitação",
                        "As cortinas e tapetes dos quartos particulares"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1421.º, n.º 1 enuncia as partes imperativamente comuns de estrutura física do prédio urbano.",
                    articleId = 1421,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 909,
                    question = "Como são repartidas as despesas de conservação e fruição das partes comuns do condomínio (Artigo 1424.º, n.º 1)?",
                    options = listOf(
                        "Em proporção do valor relativo das respetivas frações (permilagem), salvo disposição em contrário no título constitutivo",
                        "Divididas em partes absolutamente iguais entre todos os ocupantes independentemente da área",
                        "Pagas na totalidade pelo condómino com mais rendimentos",
                        "Suportadas integralmente pela junta de freguesia"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1424.º, n.º 1 estabelece a regra da proporcionalidade do valor das frações (permilagem) no pagamento de encargos comuns.",
                    articleId = 1424,
                    category = "Direitos Reais"
                ),
                LegalQuiz(
                    id = 910,
                    question = "O que caracteriza as Servidões Prediais reguladas no Artigo 1543.º do Código Civil?",
                    options = listOf(
                        "O encargo imposto num prédio em proveito exclusivo de outro prédio pertencente a dono diferente",
                        "O direito de caça em propriedades públicas sem licença",
                        "A cedência gratuita da casa para habitação de turistas",
                        "A obrigação de plantar árvores frutíferas em todos os quintais"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1543.º define a servidão predial como encargo real imposto num prédio serviente em benefício de um prédio dominante pertencente a dono distinto.",
                    articleId = 1543,
                    category = "Direitos Reais"
                )
            )
        ),

        // LEVEL 10
        QuizLevel(
            levelId = 10,
            title = "Nível 10: Direito da Família & Sucessões",
            subtitle = "Regimes de Bens, Divórcio, Herdeiros e Testamento",
            bookReference = "Livros IV e V (Art. 1576.º a 2334.º)",
            description = "Estatuto conjugal, regimes patrimoniais, tutela da legítima sucessória e validade testamentária.",
            iconName = "family_restroom",
            questions = listOf(
                LegalQuiz(
                    id = 1001,
                    question = "Qual é o regime supletivo de bens do casamento em Portugal quando os cônjuges não celebram convenção antenupcial (Artigo 1717.º)?",
                    options = listOf(
                        "Comunhão de adquiridos",
                        "Separação absoluta de bens",
                        "Comunhão geral de bens",
                        "Regime feudal de dote"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1717.º do Código Civil estabelece expressamente a comunhão de adquiridos como regime legal supletivo.",
                    articleId = 1717,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1002,
                    question = "No regime da comunhão de adquiridos (Artigo 1722.º, n.º 1), são bens próprios de cada cônjuge:",
                    options = listOf(
                        "Os bens que cada um tiver ao tempo da celebração do casamento e os que lhe advierem depois por sucessão ou doação",
                        "Todos os salários ganhos durante o casamento",
                        "Os bens comprados com o dinheiro dos rendimentos do trabalho do casal",
                        "Apenas os automóveis matriculados no estrangeiro"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1722.º, n.º 1 determina que são bens próprios os que já pertenciam ao cônjuge antes do casamento e os adquiridos a título gratuito (herança/doação).",
                    articleId = 1722,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1003,
                    question = "A alienação ou oneração da casa de morada de família exige o consentimento de ambos os cônjuges (Artigo 1682.º-A, n.º 2)?",
                    options = listOf(
                        "Sim, qualquer que seja o regime de bens (mesmo na separação de bens)",
                        "Apenas se o casamento for sob o regime da comunhão geral",
                        "Não, o proprietário pode vender livremente sem conhecimento do outro cônjuge",
                        "Apenas se houver filhos menores com mais de 10 anos"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1682.º-A, n.º 2 protege a estabilidade familiar exigindo consentimento de ambos os cônjuges para alienar a casa de morada de família em qualquer regime.",
                    articleId = 1682,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1004,
                    question = "Quais são as duas modalidades de Divórcio admitidas pelo Código Civil Português (Artigo 1773.º)?",
                    options = listOf(
                        "Divórcio por mútuo consentimento e divórcio sem consentimento de um dos cônjuges",
                        "Divórcio obrigatório aos 10 anos e divórcio facultativo",
                        "Divórcio religioso perpétuo e divórcio militar",
                        "Divórcio sumário com indemnização fixa de 50 mil euros"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 1773.º prevê as duas formas legais: por mútuo consentimento (na conservatória ou tribunal) e sem consentimento de um dos cônjuges (ação judicial).",
                    articleId = 1773,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1005,
                    question = "Quando e onde se abre a Sucessão por morte segundo o Artigo 2031.º do Código Civil?",
                    options = listOf(
                        "No momento da morte do seu autor e no lugar do seu último domicílio",
                        "No dia da leitura do testamento no cartório notarial",
                        "30 dias após o funeral no cemitério municipal",
                        "Apenas quando os herdeiros chegarem a acordo unânime"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 2031.º fixa a abertura da sucessão no momento exato do falecimento e no lugar do último domicílio do de cujus.",
                    articleId = 2031,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1006,
                    question = "Quem são os Herdeiros Legitimários inafastáveis da sucessão nos termos do Artigo 2157.º do Código Civil?",
                    options = listOf(
                        "O cônjuge, os descendentes (filhos/netos) e os ascendentes (pais/avós)",
                        "Irmãos, tios e primos em qualquer grau",
                        "Apenas os amigos mais próximos nomeados no testamento",
                        "O Estado português e as autarquias locais"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 2157.º elenca como herdeiros legitimários o cônjuge, os descendentes e os ascendentes titulares da legítima legal.",
                    articleId = 2157,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1007,
                    question = "O que é a Legítima dos herdeiros legitimários nos termos do Artigo 2156.º do Código Civil?",
                    options = listOf(
                        "A porção de bens de que o testador não pode dispor, por ser legalmente destinada aos herdeiros legitimários",
                        "Uma pensão mensal paga pela segurança social após o luto",
                        "A quantia necessária para pagar as dívidas fiscais do falecido",
                        "O valor das roupas e objetos de uso pessoal do de cujus"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 2156.º define a legítima como a quota indisponível de bens reservada imperativamente por lei aos herdeiros legitimários.",
                    articleId = 2156,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1008,
                    question = "O autor da sucessão pode deserdar um herdeiro legitimário por vontade arbitrária no testamento (Artigo 2166.º)?",
                    options = listOf(
                        "Não, a deserdação só é admitida com base nas causas graves expressamente tipificadas na lei (ex: condenação por crime doloso contra o testador)",
                        "Sim, pode deserdar qualquer filho apenas por antipatia pessoal",
                        "Sim, desde que distribua o património pelos vizinhos",
                        "Não, a deserdação é sempre proibida mesmo em caso de homicídio"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 2166.º estabelece as causas taxativas de deserdação (como crimes dolosos contra o autor da sucessão ou recusa de alimentos).",
                    articleId = 2166,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1009,
                    question = "Quais são as formas comuns de Testamento admitidas no Código Civil Português (Artigo 2204.º a 2206.º)?",
                    options = listOf(
                        "O testamento público (escrito pelo notário no livro de notas) e o testamento cerrado (escrito e assinado pelo testador e aprovado por notário)",
                        "Testamento verbal gravado em vídeo de telemóvel simples",
                        "Mensagem de WhatsApp partilhada num grupo de família",
                        "Documento assinado em guardanapo com duas testemunhas"
                    ),
                    correctIndex = 0,
                    explanation = "Os Artigos 2204.º e ss. consagram como formas testamentárias solenes comuns o testamento público e o testamento cerrado.",
                    articleId = 2204,
                    category = "Família & Sucessões"
                ),
                LegalQuiz(
                    id = 1010,
                    question = "Quais são as duas modalidades de aceitação da herança previstas no Artigo 2052.º do Código Civil?",
                    options = listOf(
                        "Aceitação pura e simples ou a benefício de inventário (em que só respondem pelas dívidas os bens inventariados)",
                        "Aceitação parcial e aceitação com desconto de 50%",
                        "Aceitação compulsória e aceitação judicial tácita perpétua",
                        "Aceitação com renúncia a todos os direitos de propriedade"
                    ),
                    correctIndex = 0,
                    explanation = "O Artigo 2052.º, n.º 1 e 2071.º regulam a aceitação pura e simples e a benefício de inventário, protegendo o património pessoal do herdeiro contra as dívidas da herança.",
                    articleId = 2052,
                    category = "Família & Sucessões"
                )
            )
        )
    )
}
