package net.japoness.emailcount;

import java.util.HashSet;
import java.util.Set;

/**
 * Service for reporting the number of distinct email addresses after applying
 * gmail style processing.
 * <p>
 * Gmail ignores dots in the username portion of email addresses, and it ignores
 * the plus sign and what follows it.
 */
public class EmailCount {
	/**
	 * Report the number of distinct addresses in the given array after applying
	 * gmail rules (ignore dots and plus-suffixes in the username portion).
	 * 
	 * @param addresses the addresses to check
	 * @return the number of distinct addresses after processing
	 */
	public int countDistinctMassagedAddresses(String[] addresses) {
		Set<String> identified = new HashSet<>();
		for (String addr : addresses) {
			identified.add(massageAddress(addr));
		}
		return identified.size();
	}

	/**
	 * Given an address, process with gmail rules and return the result.
	 * <p>
	 * gmail rule processing:
	 * <ul>
	 * <li>remove dots from the username portion
	 * <li>if there's a plus sign in the username portion, remove it and all
	 * following text
	 * </ul>
	 * 
	 * @param addr the address to process
	 * @return the processed address
	 */
	String massageAddress(String addr) {
		// we only want to affect the username part so separate it from the domain name
		String[] parts = addr.split("@", 2);

		parts[0] = parts[0].replaceAll("\\.", "").replaceAll("\\+.*", "");
		
		return String.join("@", parts);
	}
}
