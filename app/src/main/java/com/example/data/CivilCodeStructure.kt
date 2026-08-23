package com.example.data

import com.example.data.model.CivilBook
import com.example.data.model.CivilCategory
import com.example.data.model.LatinLegalTerm
import com.example.data.model.LegalQuiz
import com.example.data.model.PrescriptionRule

object CivilCodeStructure {

    val BOOKS = listOf(
        CivilBook(
            id = "PARTE_GERAL",
            romanNumber = "I",
            title = "PARTE GERAL",
            subtitle = "Das leis, das pessoas, das coisas e dos factos jurídicos",
            articleRangeStart = 1,
            articleRangeEnd = 396,
            totalArticles = 396,
            description = "Contém os princípios estruturantes do direito civil português, eficácia das leis no tempo e espaço, direitos de personalidade, teoria geral do negócio jurídico e prescrição.",
            colorHex = 0xFF1A535C,
            keyTopics = listOf("Fontes do Direito", "Personalidade e Capacidade", "Bens e Coisas", "Negócio Jurídico", "Nulidades", "Prescrição e Caducidade")
        ),
        CivilBook(
            id = "LIVRO_II",
            romanNumber = "II",
            title = "DIREITO DAS OBRIGAÇÕES",
            subtitle = "Obrigações em geral e contratos em especial",
            articleRangeStart = 397,
            articleRangeEnd = 1250,
            totalArticles = 854,
            description = "Regula as relações de crédito e débito, responsabilidade civil por factos ilícitos (Art. 483.º), contratos típicos como Compra e Venda, Arrendamento, Doação, Empreitada, Mútuo e garantias.",
            colorHex = 0xFF1E3A5F,
            keyTopics = listOf("Responsabilidade Civil", "Contrato de Compra e Venda", "Arrendamento Urbano", "Empreitada", "Incumprimento e Mora", "Fiança e Hipoteca")
        ),
        CivilBook(
            id = "LIVRO_III",
            romanNumber = "III",
            title = "DIREITO DAS COISAS",
            subtitle = "Posse, propriedade, usufruto e servidões prediais",
            articleRangeStart = 1251,
            articleRangeEnd = 1575,
            totalArticles = 325,
            description = "Estatuto jurídico dos direitos reais sobre as coisas, posse, aquisição da propriedade, regime de compropriedade, regime da propriedade horizontal (condomínios), usufruto e servidões.",
            colorHex = 0xFF2B580C,
            keyTopics = listOf("Posse e Usucapião", "Direito de Propriedade", "Propriedade Horizontal (Condomínios)", "Usufruto e Habitação", "Servidões Prediais")
        ),
        CivilBook(
            id = "LIVRO_IV",
            romanNumber = "IV",
            title = "DIREITO DA FAMÍLIA",
            subtitle = "Casamento, filiação, alimentos e responsabilidades parentais",
            articleRangeStart = 1576,
            articleRangeEnd = 2023,
            totalArticles = 448,
            description = "Regulação jurídica do casamento, regimes de bens patrimoniais (comunhão de adquiridos, geral e separação), divórcio, estabelecimento da filiação, poder paternal e prestação de alimentos.",
            colorHex = 0xFF8B263E,
            keyTopics = listOf("Casamento e Regimes de Bens", "Divórcio e Partilhas", "Estabelecimento da Filiação", "Responsabilidades Parentais", "Pensão de Alimentos")
        ),
        CivilBook(
            id = "LIVRO_V",
            romanNumber = "V",
            title = "DIREITO DAS SUCESSÕES",
            subtitle = "Sucessão legítima, legitimária, testamentária e partilhas",
            articleRangeStart = 2024,
            articleRangeEnd = 2334,
            totalArticles = 311,
            description = "Transmissão patrimonial por morte: abertura da sucessão, aceitação e repúdio da herança, herdeiros legitimários inafastáveis, testamentos, legados e partilha de bens.",
            colorHex = 0xFF9E6200,
            keyTopics = listOf("Abertura da Sucessão", "Herdeiros Legitimários", "Sucessão Legítima", "Testamentos e Formas", "Deserdação e Inoficiosidade", "Partilha da Herança")
        )
    )

    val CATEGORIES = listOf(
        CivilCategory(
            id = "CAT_CONTRATOS",
            name = "Contratos & Negócios",
            subtitle = "Compra e Venda, Arrendamento, Doação, Empreitada",
            iconName = "gavel",
            bookId = "LIVRO_II",
            colorHex = 0xFF1E3A5F,
            articleRangeDescription = "Artigos 874.º a 1250.º",
            highlightedArticleIds = listOf(874, 879, 940, 1022, 1038, 1142, 1207, 1218),
            description = "Regras legais completas sobre celebração, obrigações de compradores/vendedores, senhorios/inquilinos e empreiteiros."
        ),
        CivilCategory(
            id = "CAT_RESP_CIVIL",
            name = "Responsabilidade Civil & Danos",
            subtitle = "Art. 483.º, Culpa, Risco e Indemnizações",
            iconName = "balance",
            bookId = "LIVRO_II",
            colorHex = 0xFFDC2626,
            articleRangeDescription = "Artigos 483.º a 510.º e 562.º a 572.º",
            highlightedArticleIds = listOf(483, 484, 486, 496, 499, 503, 562, 566),
            description = "Danos patrimoniais e morais, obrigação de indemnizar e acidentes de viação."
        ),
        CivilCategory(
            id = "CAT_IMOVEIS",
            name = "Imobiliário & Condomínios",
            subtitle = "Propriedade Horizontal, Posse, Usucapião, Obras",
            iconName = "apartment",
            bookId = "LIVRO_III",
            colorHex = 0xFF16A34A,
            articleRangeDescription = "Artigos 1251.º a 1438.º",
            highlightedArticleIds = listOf(1251, 1287, 1302, 1305, 1414, 1420, 1424, 1436),
            description = "Direitos e deveres de condóminos, partes comuns, assembleias de condomínio e aquisição por usucapião."
        ),
        CivilCategory(
            id = "CAT_FAMILIA",
            name = "Família & Divórcio",
            subtitle = "Regimes de Bens, Divórcio, Pensão e Alimentos",
            iconName = "family_restroom",
            bookId = "LIVRO_IV",
            colorHex = 0xFF9333EA,
            articleRangeDescription = "Artigos 1576.º a 2023.º",
            highlightedArticleIds = listOf(1576, 1698, 1717, 1721, 1773, 1779, 1878, 2003),
            description = "Estatuto matrimonial, partilha no divórcio, custódia de menores e dever de assistência a familiares."
        ),
        CivilCategory(
            id = "CAT_SUCESSOES",
            name = "Heranças & Testamentos",
            subtitle = "Legítima, Partilhas, Repúdio e Herdeiros",
            iconName = "history_edu",
            bookId = "LIVRO_V",
            colorHex = 0xFFD97706,
            articleRangeDescription = "Artigos 2024.º a 2334.º",
            highlightedArticleIds = listOf(2024, 2031, 2050, 2062, 2131, 2133, 2156, 2179),
            description = "Regras sucessórias, cálculos da quota legítima dos filhos/cônjuge e validade formal dos testamentos."
        ),
        CivilCategory(
            id = "CAT_PRESCRIÇÃO",
            name = "Prazos de Prescrição",
            subtitle = "Prazo Ordinário (20 anos), 5 anos e Caducidade",
            iconName = "schedule",
            bookId = "PARTE_GERAL",
            colorHex = 0xFF0284C7,
            articleRangeDescription = "Artigos 296.º a 333.º",
            highlightedArticleIds = listOf(298, 303, 304, 309, 310, 311, 323, 328),
            description = "Tabela de prazos legais para cobrança de dívidas, rendas, contas e extinção de direitos."
        ),
        CivilCategory(
            id = "CAT_PESSOAS",
            name = "Pessoas & Personalidade",
            subtitle = "Nome, Imagem, Maior Acompanhado, Menores",
            iconName = "person",
            bookId = "PARTE_GERAL",
            colorHex = 0xFF0D9488,
            articleRangeDescription = "Artigos 66.º a 156.º",
            highlightedArticleIds = listOf(66, 70, 72, 79, 81, 122, 138, 140),
            description = "Começo e fim da personalidade jurídica, direitos à honra e intimidade, regime do maior acompanhado."
        ),
        CivilCategory(
            id = "CAT_NEGOCIO",
            name = "Teoria do Negócio Jurídico",
            subtitle = "Vícios da Vontade, Erro, Dolo, Nulidade",
            iconName = "fact_check",
            bookId = "PARTE_GERAL",
            colorHex = 0xFF4F46E5,
            articleRangeDescription = "Artigos 217.º a 295.º",
            highlightedArticleIds = listOf(217, 224, 227, 240, 247, 251, 253, 280, 286, 287),
            description = "Culpa na formação dos contratos (Art. 227.º), simulação, dolo, usura e distinção entre nulidade e anulabilidade."
        ),
        CivilCategory(
            id = "CAT_GARANTIAS",
            name = "Garantias & Cumprimento",
            subtitle = "Fiança, Hipoteca, Penhor e Retenção",
            iconName = "shield",
            bookId = "LIVRO_II",
            colorHex = 0xFF059669,
            articleRangeDescription = "Artigos 601.º a 761.º",
            highlightedArticleIds = listOf(601, 627, 638, 655, 686, 754, 762, 804),
            description = "Garantias gerais dos credores, benefício da excussão prévia do fiador e direito de retenção de bens."
        ),
        CivilCategory(
            id = "CAT_FONTES",
            name = "Fontes & Aplicação da Lei",
            subtitle = "Vigência, Interpretação, Analogia e Abuso",
            iconName = "account_balance",
            bookId = "PARTE_GERAL",
            colorHex = 0xFF334155,
            articleRangeDescription = "Artigos 1.º a 13.º e 334.º",
            highlightedArticleIds = listOf(1, 5, 6, 9, 10, 12, 13, 334),
            description = "Regras basilares sobre entrada em vigor das leis, retroactividade, princípios de interpretação e abuso do direito."
        )
    )

    val LATIN_TERMS = listOf(
        LatinLegalTerm(
            term = "Pacta sunt servanda",
            translation = "Os pactos devem ser cumpridos",
            explanation = "Princípio fundamental da pontualidade e vinculatividade dos contratos consagrado no Artigo 406.º, n.º 1 do Código Civil.",
            articleRef = 406
        ),
        LatinLegalTerm(
            term = "Rebus sic stantibus",
            translation = "Estando assim as coisas",
            explanation = "Cláusula implícita que permite a resolução ou modificação do contrato por alteração anormal das circunstâncias (Artigo 437.º).",
            articleRef = 437
        ),
        LatinLegalTerm(
            term = "Venire contra factum proprium",
            translation = "Vir contra o próprio facto",
            explanation = "Modalidade proibida de abuso do direito (Artigo 334.º) decorrente da violação do princípio da confiança e boa fé.",
            articleRef = 334
        ),
        LatinLegalTerm(
            term = "Culpa in contrahendo",
            translation = "Culpa na formação do contrato",
            explanation = "Responsabilidade pré-contratual por violação dos deveres de boa fé nas negociações preliminares (Artigo 227.º).",
            articleRef = 227
        ),
        LatinLegalTerm(
            term = "Aliud pro alio",
            translation = "Uma coisa por outra",
            explanation = "Entrega de coisa totalmente diversa daquela que foi convencionada na compra e venda (Artigo 913.º e ss.).",
            articleRef = 913
        ),
        LatinLegalTerm(
            term = "In dubio contra stipulatorem",
            translation = "Na dúvida, contra quem estipulou",
            explanation = "Regra de interpretação contratual que protege a parte aderente perante cláusulas redigidas de modo ambíguo.",
            articleRef = 236
        ),
        LatinLegalTerm(
            term = "Superficies solo cedit",
            translation = "A superfície cede ao solo",
            explanation = "Princípio da acessão industrial imobiliária segundo o qual o proprietário do solo adquire o que nele for edificado (Artigo 1325.º).",
            articleRef = 1325
        ),
        LatinLegalTerm(
            term = "Mora creditoris / debitoris",
            translation = "Mora do credor / do devedor",
            explanation = "Atraso ilícito ou recusa injustificada no cumprimento da prestação ou no seu recebimento (Artigos 804.º e 813.º).",
            articleRef = 804
        )
    )

    val PRESCRIPTION_RULES = listOf(
        PrescriptionRule(
            title = "Prazo Ordinário Geral",
            durationText = "20 anos",
            durationYears = 20f,
            articleId = 309,
            description = "É o prazo geral aplicável a todos os créditos e direitos para os quais a lei não fixe prazo menor.",
            examples = listOf("Dívidas de contratos comuns sem prazo especial", "Indemnizações contratuais gerais", "Obrigações civis não periódicas")
        ),
        PrescriptionRule(
            title = "Prazo de Cinco Anos (Prestações Periódicas)",
            durationText = "5 anos",
            durationYears = 5f,
            articleId = 310,
            description = "Prescrevem no prazo de cinco anos as anuidades, pensões e pagamentos sucessivos que devam ser satisfeitos anualmente ou em prazos mais curtos.",
            examples = listOf("Rendas de locação/arrendamento", "Juros convencionais ou legais", "Pensões de alimentos vencidas", "Quotas de condomínio e despesas periódicas", "Prestações de amortização de capital com juros")
        ),
        PrescriptionRule(
            title = "Responsabilidade Extracontratual (Factos Ilícitos)",
            durationText = "3 anos",
            durationYears = 3f,
            articleId = 498,
            description = "O direito de indemnização prescreve no prazo de três anos, a contar da data em que o lesado teve conhecimento do direito que lhe compete.",
            examples = listOf("Acidentes de viação (danos próprios/lesado)", "Ofensas à integridade física ou património", "Danos por negligência ou responsabilidade pelo risco")
        ),
        PrescriptionRule(
            title = "Prescrições Presuntivas - 6 Meses",
            durationText = "6 meses",
            durationYears = 0.5f,
            articleId = 316,
            description = "Presumem-se pagas no prazo de 6 meses as dívidas de alojamento e alimentação em estabelecimentos hoteleiros.",
            examples = listOf("Alojamento hoteleiro", "Contas de restaurante e refeições servidas")
        ),
        PrescriptionRule(
            title = "Prescrições Presuntivas - 2 Anos",
            durationText = "2 anos",
            durationYears = 2f,
            articleId = 317,
            description = "Prescrevem presuntivamente no prazo de 2 anos os créditos de comerciantes por mercadorias a não comerciantes e serviços profissionais liberais.",
            examples = listOf("Honorários de médicos, advogados e engenheiros", "Vendas de bens a consumidores finais", "Serviços prestados no exercício de profissões liberais")
        ),
        PrescriptionRule(
            title = "Anulação de Negócio Jurídico",
            durationText = "1 ano",
            durationYears = 1f,
            articleId = 287,
            description = "A acção de anulação deve ser proposta no prazo de um ano subsequente à cessação do vício (coacção) ou ao conhecimento do vício (erro/dolo).",
            examples = listOf("Contrato assinado sob erro essencial", "Negócio viciado por dolo ou incapacidade acidental")
        )
    )

    val QUIZ_QUESTIONS = listOf(
        LegalQuiz(
            id = 1,
            question = "Segundo o Artigo 483.º do Código Civil, quais são os pressupostos cumulativos da responsabilidade civil por factos ilícitos?",
            options = listOf(
                "Facto voluntário, ilicitude, culpa, dano e nexo de causalidade entre o facto e o dano",
                "Apenas a existência de dano e culpa grave da vítima",
                "Contrato prévio por escrito e registo predial válido",
                "Comportamento involuntário e enriquecimento sem causa"
            ),
            correctIndex = 0,
            explanation = "O Artigo 483.º, n.º 1 consagra o princípio geral da responsabilidade delitual exigindo facto, ilicitude, culpa, dano e nexo causal.",
            articleId = 483,
            category = "Responsabilidade Civil"
        ),
        LegalQuiz(
            id = 2,
            question = "Qual é o regime supletivo de bens no casamento celebrado em Portugal na falta de convenção antenupcial?",
            options = listOf(
                "Separação de bens",
                "Comunhão geral de bens",
                "Comunhão de adquiridos",
                "Regime dotal"
            ),
            correctIndex = 2,
            explanation = "Nos termos do Artigo 1717.º do Código Civil, na falta de convenção antenupcial, o casamento considera-se celebrado sob o regime da comunhão de adquiridos.",
            articleId = 1717,
            category = "Direito da Família"
        ),
        LegalQuiz(
            id = 3,
            question = "Qual é o prazo ordinário da prescrição de créditos previsto no Artigo 309.º do Código Civil Português?",
            options = listOf(
                "5 anos",
                "10 anos",
                "20 anos",
                "30 anos"
            ),
            correctIndex = 2,
            explanation = "O Artigo 309.º estipula expressamente: 'O prazo ordinário da prescrição é de vinte anos.'",
            articleId = 309,
            category = "Parte Geral"
        ),
        LegalQuiz(
            id = 4,
            question = "Nos termos do Artigo 227.º do Código Civil (culpa na formação dos contratos), que dever basilar vincula as partes?",
            options = listOf(
                "Dever de celebrar imediatamente o contrato definitivo",
                "Proceder segundo as regras da boa fé tanto nos preliminares como na formação",
                "Pagar sinal monetário obrigatório na fase de negociação",
                "Não consultar outros concorrentes de mercado"
            ),
            correctIndex = 1,
            explanation = "O Artigo 227.º consagra a responsabilidade pré-contratual (culpa in contrahendo) obrigando as partes a agir com boa fé.",
            articleId = 227,
            category = "Teoria do Negócio"
        ),
        LegalQuiz(
            id = 5,
            question = "Na compra e venda civil de bens imóveis em Portugal, que forma legal é exigida como regra pelo Artigo 875.º?",
            options = listOf(
                "Apenas acordo verbal entre as partes com duas testemunhas",
                "Escritura pública ou documento particular autenticado",
                "Mensagem de correio eletrónico com assinatura digital simples",
                "Registo notarial provisório com recibo de quitação"
            ),
            correctIndex = 1,
            explanation = "O Artigo 875.º do CC determina que o contrato de compra e venda de bens imóveis só é válido se for celebrado por escritura pública ou por documento particular autenticado.",
            articleId = 875,
            category = "Contratos"
        ),
        LegalQuiz(
            id = 6,
            question = "Quem são os herdeiros legitimários inafastáveis da sucessão nos termos do Artigo 2157.º do Código Civil?",
            options = listOf(
                "Irmãos, sobrinhos e tios",
                "O cônjuge, os descendentes e os ascendentes",
                "Apenas os filhos de primeiro grau",
                "O Estado português e a Santa Casa da Misericórdia"
            ),
            correctIndex = 1,
            explanation = "São herdeiros legitimários o cônjuge, os descendentes e os ascendentes, pela ordem e segundo as regras da sucessão legítima (Art. 2157.º).",
            articleId = 2157,
            category = "Direito das Sucessões"
        )
    )
}
