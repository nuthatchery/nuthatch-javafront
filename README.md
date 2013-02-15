## Nuthatch/J+JavaFront

This project contains the relevant syntax definitions for the
[JavaFront](http://strategoxt.org/Stratego/JavaFront) parser originally
developed for [Stratego](http://strategoxt.org/).

JavaFront adds support for Java program transformation to Nuthatch/J, by
providing a handcrafted SDF syntax definition for Java (J2SE 5.0).

The syntax definitions are from JavaFront 0.9.1. The original
code can be found in the ['original' branch in Git](https://github.com/nuthatchery/nuthatch-javafront/tree/original).

You need the [StrategoXT tool chain](http://strategoxt.org/) installed in order to compile
the syntax definitions into a parse table. This is only necessary
if you actually change the definitions, since a compiled parse table
is included here.

To use, you also need:

1. Nuthatch/J, and Nuthatch/J+Stratego
2. The Stratego term library:
     [`org.spoofax.terms`](https://svn.strategoxt.org/repos/StrategoXT/spoofax/trunk/spoofax/org.spoofax.terms)
2. JSGLR: 
     [`org.spoofax.jsglr`](https://svn.strategoxt.org/repos/StrategoXT/spoofax/trunk/spoofax/org.spoofax.jsglr)

## Documentation

Documentation is available at the [Nuthatch website](http://nuthatchery.org/docs/).

## Warning: Experimental Software!

Nuthatch/J is currently highly experimental, and the API is changing all the time.

## Source code and bug reports

The source code is hosted on [GitHub](https://github.com/nuthatchery/nuthatch-javafront).
Please file bugs in the [GitHub Issue Tracker](https://github.com/nuthatchery/nuthatch-javafront/issues).

## License
  Authors and contributors are listed in the AUTHORS file.

### JavaFront syntax definitions
  JavaFront is Copyright © 2002-2009 Martin Bravenboer
  
  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2 of the License, or (at your option) any later version.
  
  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.
  
  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

### Nuthatch/J adaption
  Nuthatch/J+JavaFront is Copyright © 2013 Anya Helene Bagge
  
  This library is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this library.  If not, see <http://www.gnu.org/licenses/>.
