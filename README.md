# Container Measure Service

Microsserviço responsável por gerenciar tamanhos padronizados de copos, garrafas e outros recipientes utilizados em
choperias e estabelecimentos de bebidas.

---

## Tamanhos Comuns em Choperias

### Copos (Glass Sizes)

| Nome comercial     | Tipo (Enum sugerido) | Volume (mL) | Descrição                                                            |
|--------------------|----------------------|-------------|----------------------------------------------------------------------|
| **Taster**         | `TASTER`             | 150         | Copo degustação — usado em flights ou amostras                       |
| **Half Pint**      | `HALF`               | 250         | Copo pequeno (metade de uma pint)                                    |
| **Pint**           | `PINT`               | 473         | Padrão americano — 16 oz                                             |
| **Imperial Pint**  | `IMPERIAL_PINT`      | 568         | Padrão inglês                                                        |
| **Tulip**          | `TULIP`              | 330         | Copo arredondado, usado para IPAs e Belgian ales                     |
| **Snifter**        | `SNIFTER`            | 300         | Copo arredondado para cervejas fortes e aromáticas (ex: Barley Wine) |
| **Weizen**         | `WEIZEN`             | 500         | Copo alto para cervejas de trigo                                     |
| **Stein**          | `STEIN`              | 1000        | Caneca grande tradicional alemã (1 litro)                            |
| **Growler (Copo)** | `MINI_GROWLER`       | 1000        | Versão menor de growler para consumo imediato                        |

### Garrafas e Growlers (Bottle Sizes)

| Nome comercial | Tipo (Enum sugerido) | Volume (mL) | Descrição                                   |
|----------------|----------------------|-------------|---------------------------------------------|
| **Stubby**     | `STUBBY`             | 330         | Garrafa pequena tradicional (ex: long neck) |
| **Bomber**     | `BOMBER`             | 650         | Garrafa artesanal intermediária             |
| **Crowler**    | `CROWLER`            | 946         | Lata de chope envasada sob pressão (32 oz)  |
| **Growler**    | `GROWLER`            | 1890        | Recipiente retornável para chope            |
| **Howler**     | `HOWLER`             | 950         | Metade de um growler                        |
| **Mini Keg**   | `MINI_KEG`           | 5000        | Barril pequeno (5 L) para consumo doméstico |
