scalajs-dice is a dice roller in Scala JS.

## How to run locally

    bin/sbt
    > ~fastOptJS

Navigate to: js/target/scala-2.13/classes/index-dev.html

Or download devd from https://github.com/cortesi/devd and run:

    devd -ol js/target/scala-2.13/
    and navigate to: http://localhost:8000/classes/index-dev.html

Use `sbt fullOptJS` and open `index-opt.html` for the optimized version.

## License

Copyright (C) 2021 Inigo Surguy

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
