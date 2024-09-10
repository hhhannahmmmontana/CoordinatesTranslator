# CoordinatesTranslator
Простая штука для перевод координат в разные системы координат<br>
Спасибо [Косте Майнкрафт (k0ch3gar)](https://github.com/k0ch3gar/) за помощь с формулами!
## Формулы
| Из ${\downarrow}$ в ${\rightarrow}$ | Декартовы | Полярные | Цилиндрические | Сферические |
| - | - | - | - | - |
| Декартовы | - | ${\rho = \sqrt{x^2 + y^2}}$<br>${\phi=arctan2(y,x)}$ | ${\rho=\sqrt{x^2+y^2}}$<br>${\phi=arctan2(y,x)}$<br>${z=z}$ | ${r=\sqrt{x^2+y^2}}$<br>${\theta=arctan\left(\frac{\sqrt{x^2+y^2}}{z}\right)}$<br>${\phi=arctan2(y,x)}$ |
| Полярные | ${x=\rho\cos\phi}$<br>${y=\rho\sin\phi}$ | - | - | - |
| Цилиндрические | ${x=\rho\cos\phi}$<br>${y=\rho\sin\phi}$<br>${z=z}$ | - | - | ${r = \sqrt{\rho^2+z^2}}$<br>${\theta=arctan2(\rho,z)}$<br>${\phi=\phi}$ |
| Сферические | ${x=r\sin\theta\cos\phi}$<br>${y=r\sin\theta\sin\phi}$<br>${z=r\cos\theta}$ | - | ${\rho=r\sin\theta}$<br>${\phi=\phi}$<br>${z=r\cos\theta}$ | - |
