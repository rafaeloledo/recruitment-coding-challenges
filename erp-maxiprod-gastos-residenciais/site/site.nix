{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
	packages = with pkgs; [
		nodejs_22 # already shipped with npm
	];
	NIX_SHL_LVL = 1;
	shellHook = ''
	'';
}
