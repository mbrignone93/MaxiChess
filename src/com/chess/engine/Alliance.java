/*
 * Powered by Maximiliano Brignone
 * © 2021 https://github.com/mbrignone93
 * 
 * */

package com.chess.engine;

public enum Alliance 
{
	WHITE
	{
		@Override
		public int getDirection() 
		{
			return -1;
		}

		@Override
		public boolean isWhite()
		{
			return true;
		}

		@Override
		public boolean isBlack() 
		{
			return false;
		}
	},
	BLACK
	{
		@Override
		public int getDirection()
		{
			return 1;
		}

		@Override
		public boolean isWhite() 
		{
			return false;
		}

		@Override
		public boolean isBlack() 
		{
			return true;
		}
	};   
	
	public abstract int getDirection();
	
	public abstract boolean isWhite();
	
	public abstract boolean isBlack();
}
